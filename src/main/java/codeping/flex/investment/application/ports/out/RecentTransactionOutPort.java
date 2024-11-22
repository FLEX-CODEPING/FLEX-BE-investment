package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.domain.model.stockportfolio.RecentTransaction;

import java.util.Optional;

public interface RecentTransactionOutPort {

    RecentTransaction saveRecentTransaction(RecentTransaction recentTransaction) ;
    Optional<RecentTransaction> getRecentTransactionByUserId(final Long userId);
}
