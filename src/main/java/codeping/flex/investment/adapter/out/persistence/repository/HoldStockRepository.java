package codeping.flex.investment.adapter.out.persistence.repository;

import codeping.flex.investment.adapter.out.persistence.entity.HoldStockEntity;
import codeping.flex.investment.domain.constant.HoldStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoldStockRepository extends JpaRepository<HoldStockEntity, Long> {

    Optional<HoldStockEntity> findByUserIdAndStockCode(Long userId, String stockCode);
    Slice<HoldStockEntity> findAllByUserIdAndHoldStatus(Long userId, HoldStatus holdStatus, Pageable pageable);
}
