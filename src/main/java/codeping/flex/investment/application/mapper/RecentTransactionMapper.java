package codeping.flex.investment.application.mapper;

import codeping.flex.investment.domain.model.RecentTransaction;
import codeping.flex.investment.domain.model.Transaction;

public class RecentTransactionMapper {

    private RecentTransactionMapper() {
        throw new IllegalArgumentException();
    }

    public static RecentTransaction mapToRecentTransaction(Long userId, Transaction transaction) {
        return RecentTransaction.builder()
                .userId(userId)
                .transaction(transaction)
                .build();
    }
}
