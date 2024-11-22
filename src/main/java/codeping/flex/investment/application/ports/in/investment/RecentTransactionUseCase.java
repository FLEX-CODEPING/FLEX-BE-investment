package codeping.flex.investment.application.ports.in.investment;

import codeping.flex.investment.domain.model.stockportfolio.RecentTransaction;
import codeping.flex.investment.domain.model.stockportfolio.Transaction;

public interface RecentTransactionUseCase {

    RecentTransaction saveRecentTransaction(Long userId, Transaction transaction);
    RecentTransaction getRecentTransactionByUserId(Long userId);
    RecentTransaction updateRecentTransaction(Long userId, Transaction transaction);
}
