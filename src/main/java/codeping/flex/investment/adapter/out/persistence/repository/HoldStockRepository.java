package codeping.flex.investment.adapter.out.persistence.repository;

import codeping.flex.investment.adapter.out.persistence.entity.transaction.HoldStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldStockRepository extends JpaRepository<HoldStockEntity, Long> {
}
