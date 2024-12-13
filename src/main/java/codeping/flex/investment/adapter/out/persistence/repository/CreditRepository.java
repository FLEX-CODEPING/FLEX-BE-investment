package codeping.flex.investment.adapter.out.persistence.repository;

import codeping.flex.investment.adapter.out.persistence.entity.CreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<CreditEntity, Long> {

    Optional<CreditEntity> findFirstByUserId(Long userId);
}
