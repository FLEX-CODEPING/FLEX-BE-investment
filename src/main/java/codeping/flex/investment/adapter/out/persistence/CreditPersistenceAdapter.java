package codeping.flex.investment.adapter.out.persistence;

import codeping.flex.investment.adapter.out.persistence.entity.CreditEntity;
import codeping.flex.investment.adapter.out.persistence.mapper.CreditPersistenceMapper;
import codeping.flex.investment.adapter.out.persistence.repository.CreditRepository;
import codeping.flex.investment.application.ports.out.CreditOutPort;
import codeping.flex.investment.domain.model.Credit;
import codeping.flex.investment.global.annotation.architecture.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class CreditPersistenceAdapter implements CreditOutPort {

    private final CreditRepository creditRepository;
    private final CreditPersistenceMapper creditPersistenceMapper;

    @Override
    public Credit saveCredit(Credit credit) {
        CreditEntity creditEntity = this.creditPersistenceMapper.toEntity(credit);
        CreditEntity savedEntity = this.creditRepository.save(creditEntity);
        return this.creditPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Credit> getFirstCreditByUserId(Long userId) {
        Optional<CreditEntity> creditEntity = this.creditRepository.findFirstByUserId(userId);
        return creditEntity.map(this.creditPersistenceMapper::toDomain);
    }
}
