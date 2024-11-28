package codeping.flex.investment.adapter.out.persistence;

import codeping.flex.investment.adapter.out.persistence.entity.HoldStockEntity;
import codeping.flex.investment.adapter.out.persistence.mapper.HoldStockPersistenceMapper;
import codeping.flex.investment.adapter.out.persistence.repository.HoldStockRepository;
import codeping.flex.investment.application.ports.out.HoldStockOutPort;
import codeping.flex.investment.domain.constant.HoldStatus;
import codeping.flex.investment.domain.model.HoldStock;
import codeping.flex.investment.global.annotation.architecture.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

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
    public Optional<HoldStock> getHoldStockByUserIdAndStockCode(Long userId, String stockCode) {
        final Optional<HoldStockEntity> holdStockEntity = holdStockRepository.findByUserIdAndStockCode(userId, stockCode);
        return holdStockEntity.map(this.holdStockPersistenceMapper::toDomain);
    }

    @Override
    public Slice<HoldStock> getHoldStocksByUserIdAndHoldStatus(Long userId, HoldStatus holdStatus, Pageable pageable) {
        return holdStockRepository.findAllByUserIdAndHoldStatus(userId, holdStatus, pageable).map(this.holdStockPersistenceMapper::toDomain);
    }
}
