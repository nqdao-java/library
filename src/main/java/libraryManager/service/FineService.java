package libraryManager.service;
import libraryManager.entity.Fine;
import libraryManager.entity.Loan;
import libraryManager.repository.FineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FineService {

    private final FineRepository fineRepository;

    public FineService(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    public List<Fine> listAll() { return fineRepository.findAll(); }

    public List<Fine> openFines() { return fineRepository.findByPaidFalse(); }

    @Transactional
    public Fine createFine(Loan loan, int overdueDays, BigDecimal perDay) {
        BigDecimal amount = perDay.multiply(BigDecimal.valueOf(overdueDays));
        Fine fine = new Fine();
        fine.setLoan(loan);
        fine.setAmount(amount);
        fine.setPaid(false);
        return fineRepository.save(fine);
    }

    @Transactional
    public void payFine(Long id) {
        Fine f = fineRepository.findById(id).orElseThrow();
        f.setPaid(true);
        f.setPaidAt(LocalDateTime.now());
        fineRepository.save(f);
    }
}
