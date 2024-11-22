package codeping.flex.investment.adapter.out.persistence.repository;

import codeping.flex.investment.adapter.out.persistence.entity.RecentTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecentTransactionRepository extends JpaRepository<RecentTransactionEntity, Long> {

    Optional<RecentTransactionEntity> findByUserId(Long userId);
}
