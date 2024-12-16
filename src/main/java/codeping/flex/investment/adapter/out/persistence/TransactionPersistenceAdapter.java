package codeping.flex.investment.adapter.out.persistence;

import codeping.flex.investment.adapter.in.web.data.investment.response.RankingResponse;
import codeping.flex.investment.adapter.out.persistence.entity.TransactionEntity;
import codeping.flex.investment.adapter.out.persistence.mapper.TransactionPersistenceMapper;
import codeping.flex.investment.adapter.out.persistence.repository.TransactionRepository;
import codeping.flex.investment.application.ports.out.TransactionOutPort;
import codeping.flex.investment.domain.model.Transaction;
import codeping.flex.investment.global.annotation.architecture.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

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

    @Override
    public Slice<Transaction> getAllTransactionsByUserId(final Long userId, Pageable pageable) {
        return this.transactionRepository.findAllByUserId(userId, pageable).map(this.transactionPersistenceMapper::toDomain);
    }

    @Override
    public List<RankingResponse> getRankings(Pageable pageable) {
        return transactionRepository.getRankingOrderByTotalProfit(pageable);
    }
}
