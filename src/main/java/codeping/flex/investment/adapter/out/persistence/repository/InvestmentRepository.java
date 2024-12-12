package codeping.flex.investment.adapter.out.persistence.repository;

import codeping.flex.investment.adapter.out.persistence.entity.InvestmentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentRepository extends JpaRepository<InvestmentEntity, Long> {

    Slice<InvestmentEntity> findAllByUserIdAndStockCode(Long userId, String stockCode, Pageable pageable);
    List<InvestmentEntity> findAllByUserId(Long userId);
    long countByUserId(Long userId);
}
