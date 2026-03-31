package libraryManager.service;
import libraryManager.entity.SystemSetting;
import libraryManager.repository.SystemSettingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemSettingService {

    private final SystemSettingRepository repo;

    public SystemSettingService(SystemSettingRepository repo) { this.repo = repo; }

    public List<SystemSetting> listAll() { return repo.findAll(); }

    public SystemSetting save(SystemSetting s) { return repo.save(s); }

    public void delete(Long id) { repo.deleteById(id); }

    public SystemSetting findByKey(String key) { return repo.findByKeyName(key).orElse(null); }
}
