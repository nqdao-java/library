// File: src/main/resources/static/js/api-client.js

// Cấu hình Base URL cho toàn bộ dự án
const API_BASE_URL = '/api';

async function fetchWithAuth(endpoint, options = {}) {
    // 1. Móc JWT từ két sắt ra
    const token = localStorage.getItem('jwt_token');

    // 2. Chuẩn bị Headers chuẩn
    const headers = {
        'Content-Type': 'application/json',
        ...options.headers // Gộp thêm header truyền từ ngoài vào (nếu có)
    };

    // 3. Nếu có Token thì gắn vào Authorization
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }

    // 4. Tiến hành gọi API
    try {
        const response = await fetch(`${API_BASE_URL}${endpoint}`, {
            ...options,
            headers: headers
        });

        // ==========================================
        // XỬ LÝ LỖI BẢO MẬT TẬP TRUNG (GLOBAL ERROR)
        // ==========================================

        if (response.status === 401) {
            // Lỗi 401: Chưa đăng nhập hoặc Token đã hết hạn
            console.warn("Phiên đăng nhập không hợp lệ hoặc đã hết hạn.");
            localStorage.removeItem('jwt_token'); // Xóa token cũ cho sạch
            window.location.href = '/login'; // Đá văng về trang đăng nhập
            return null; // Dừng thực thi
        }

        if (response.status === 403) {
            // Lỗi 403: Đã đăng nhập nhưng không đủ quyền (VD: Sinh viên đòi vào trang Admin)
            alert("Bạn không có quyền (Role) để thực hiện thao tác này!");
            return null;
        }

        // Trả về response nguyên bản để các trang tự xử lý tiếp (thành công hoặc lỗi nghiệp vụ khác)
        return response;

    } catch (error) {
        console.error("Lỗi Network / CORS:", error);
        throw error; // Ném lỗi ra ngoài cho trang HTML tự bắt
    }
}