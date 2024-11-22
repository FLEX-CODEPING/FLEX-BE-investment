package codeping.flex.investment.adapter.out.persistence;

import codeping.flex.investment.adapter.out.persistence.entity.stockportfolio.TransactionEntity;
import codeping.flex.investment.adapter.out.persistence.mapper.TransactionPersistenceMapper;
import codeping.flex.investment.adapter.out.persistence.repository.TransactionRepository;
import codeping.flex.investment.application.ports.out.TransactionOutPort;
import codeping.flex.investment.domain.model.stockportfolio.Transaction;
import codeping.flex.investment.global.annotation.architecture.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class TransactionPersistenceAdapter implements TransactionOutPort {

    private final TransactionRepository transactionRepository;
    private final TransactionPersistenceMapper transactionPersistenceMapper;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        TransactionEntity transactionEntity = this.transactionPersistenceMapper.toEntity(transaction);
        TransactionEntity savedEntity = this.transactionRepository.save(transactionEntity);
        return this.transactionPersistenceMapper.toDomain(savedEntity);
    }
}
