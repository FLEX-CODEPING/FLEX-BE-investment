package codeping.flex.investment.application.ports.in.investment.domain;

import codeping.flex.investment.domain.model.RecentTransaction;
import codeping.flex.investment.domain.model.Transaction;

public interface RecentTransactionUseCase {

    RecentTransaction saveRecentTransaction(Long userId, Transaction transaction);
    RecentTransaction getRecentTransactionByUserId(Long userId);
    RecentTransaction updateRecentTransaction(Long userId, Transaction transaction);
}
