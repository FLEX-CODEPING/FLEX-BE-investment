package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.domain.model.RecentTransaction;

import java.util.Optional;

public interface RecentTransactionOutPort {

    /** SAVE **/
    RecentTransaction saveRecentTransaction(RecentTransaction recentTransaction) ;

    /** GET **/
    Optional<RecentTransaction> getRecentTransactionByUserId(final Long userId);
}
