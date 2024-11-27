package codeping.flex.investment.adapter.out.persistence.repository;

import codeping.flex.investment.adapter.out.persistence.entity.HoldStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoldStockRepository extends JpaRepository<HoldStockEntity, Long> {

    Optional<HoldStockEntity> findByUserIdAndStockCode(Long userId, String stockCode);
}
