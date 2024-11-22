package codeping.flex.investment.adapter.out.persistence;

import codeping.flex.investment.adapter.out.persistence.entity.InvestmentEntity;
import codeping.flex.investment.adapter.out.persistence.mapper.InvestmentPersistenceMapper;
import codeping.flex.investment.adapter.out.persistence.repository.InvestmentRepository;
import codeping.flex.investment.application.ports.out.InvestmentOutPort;
import codeping.flex.investment.domain.model.Investment;
import codeping.flex.investment.global.annotation.architecture.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class InvestmentPersistenceAdapter implements InvestmentOutPort {

    private final InvestmentRepository investmentRepository;
    private final InvestmentPersistenceMapper investmentPersistenceMapper;

    @Override
    public Investment saveInvestment(final Investment investment) {
        InvestmentEntity investmentEntity = this.investmentPersistenceMapper.toEntity(investment);
        InvestmentEntity savedEntity = this.investmentRepository.save(investmentEntity);
        return this.investmentPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Investment> getInvestmentById(final Long investmentId) {
        final Optional<InvestmentEntity> investmentEntity = this.investmentRepository.findById(investmentId);
        return investmentEntity.map(this.investmentPersistenceMapper::toDomain);
    }
}
