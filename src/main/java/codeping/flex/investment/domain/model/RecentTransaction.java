package codeping.flex.investment.domain.model;

import codeping.flex.investment.domain.model.common.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecentTransaction extends BaseTime {

    private Long recentTransactionId;
    private Long userId;
    private Transaction transaction;

    @Builder
    public RecentTransaction(Long recentTransactionId, Long userId, Transaction transaction) {
        this.recentTransactionId = recentTransactionId;
        this.userId = userId;
        this.transaction = transaction;
    }

    public void synchronizeRecentTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
