package codeping.flex.investment.adapter.out.persistence;

import codeping.flex.investment.adapter.out.persistence.entity.stockportfolio.RecentTransactionEntity;
import codeping.flex.investment.adapter.out.persistence.mapper.RecentTransactionPersistenceMapper;
import codeping.flex.investment.adapter.out.persistence.repository.RecentTransactionRepository;
import codeping.flex.investment.application.ports.out.RecentTransactionOutPort;
import codeping.flex.investment.domain.model.stockportfolio.RecentTransaction;
import codeping.flex.investment.global.annotation.architecture.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class RecentTransactionPersistenceAdapter implements RecentTransactionOutPort {

    private final RecentTransactionRepository recentTransactionRepository;
    private final RecentTransactionPersistenceMapper recentTransactionPersistenceMapper;

    public RecentTransaction saveRecentTransaction(final RecentTransaction recentTransaction) {
        RecentTransactionEntity recentTransactionEntity = this.recentTransactionPersistenceMapper.toEntity(recentTransaction);
        RecentTransactionEntity savedEntity = this.recentTransactionRepository.save(recentTransactionEntity);
        return this.recentTransactionPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<RecentTransaction> getRecentTransactionByUserId(final Long userId) {
        final Optional<RecentTransactionEntity> recentTransactionEntity = this.recentTransactionRepository.findByUserId(userId);

        if (recentTransactionEntity.isEmpty()) {
            return Optional.empty();
        }

        final RecentTransaction recentTransaction = this.recentTransactionPersistenceMapper.toDomain(recentTransactionEntity.get());
        return Optional.of(recentTransaction);
    }
}
