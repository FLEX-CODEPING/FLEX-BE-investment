package codeping.flex.investment.adapter.out.persistence.repository;

import codeping.flex.investment.adapter.out.persistence.entity.TransactionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    Slice<TransactionEntity> findAllByUserId(Long userId, Pageable pageable);
}
