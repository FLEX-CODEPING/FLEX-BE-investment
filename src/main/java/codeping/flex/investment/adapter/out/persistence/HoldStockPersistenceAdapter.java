package codeping.flex.investment.adapter.out.persistence;

import codeping.flex.investment.adapter.out.persistence.entity.stockportfolio.HoldStockEntity;
import codeping.flex.investment.adapter.out.persistence.mapper.HoldStockPersistenceMapper;
import codeping.flex.investment.adapter.out.persistence.repository.HoldStockRepository;
import codeping.flex.investment.application.ports.out.HoldStockOutPort;
import codeping.flex.investment.domain.model.stockportfolio.HoldStock;
import codeping.flex.investment.global.annotation.architecture.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class HoldStockPersistenceAdapter implements HoldStockOutPort {
    private final HoldStockRepository holdStockRepository;
    private final HoldStockPersistenceMapper holdStockPersistenceMapper;

    @Override
    public HoldStock saveHoldStock(HoldStock holdStock) {
        HoldStockEntity holdStockEntity = this.holdStockPersistenceMapper.toEntity(holdStock);
        HoldStockEntity savedEntity = this.holdStockRepository.save(holdStockEntity);
        return this.holdStockPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<HoldStock> getHoldStockById(Long holdStockId) {
        return Optional.empty();
    }

}
