package codeping.flex.investment.application.service.domain;

import codeping.flex.investment.application.ports.in.investment.RecentTransactionUseCase;
import codeping.flex.investment.application.ports.out.RecentTransactionOutPort;
import codeping.flex.investment.domain.model.stockportfolio.RecentTransaction;
import codeping.flex.investment.domain.model.stockportfolio.Transaction;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import codeping.flex.investment.global.common.exception.ApplicationException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static codeping.flex.investment.application.mapper.RecentTransactionMapper.mapToRecentTransaction;
import static codeping.flex.investment.domain.exception.RecentTransactionErrorCode.RECENT_TRANSACTION_NOT_FOUND;

@ApplicationService
@RequiredArgsConstructor
public class RecentTransactionService implements RecentTransactionUseCase {

    private final RecentTransactionOutPort recentTransactionOutPort;

    @Override
    public RecentTransaction saveRecentTransaction(Long userId, Transaction transaction) {
        RecentTransaction recentTransaction = mapToRecentTransaction(userId, transaction);
        return recentTransactionOutPort.saveRecentTransaction(recentTransaction);
    }

    @Override
    public RecentTransaction getRecentTransactionByUserId(Long userId) {
        Optional<RecentTransaction> recentTransaction = recentTransactionOutPort.getRecentTransactionByUserId(userId);

        if (recentTransaction.isEmpty()) {
            throw ApplicationException.from(RECENT_TRANSACTION_NOT_FOUND);
        }
        return recentTransaction.get();
    }

    @Override
    public RecentTransaction updateRecentTransaction(Long userId, Transaction transaction) {
        Optional<RecentTransaction> recentTransaction = recentTransactionOutPort.getRecentTransactionByUserId(userId);

        if (recentTransaction.isEmpty()) {
            throw ApplicationException.from(RECENT_TRANSACTION_NOT_FOUND);
        }

        recentTransaction.get().synchronizeRecentTransaction(transaction);
        return recentTransaction.get();
    }
}
