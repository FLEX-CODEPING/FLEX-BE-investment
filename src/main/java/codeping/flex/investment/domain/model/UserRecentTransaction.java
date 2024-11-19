package codeping.flex.investment.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRecentTransaction {
    private Long id;
    private Long userId;
    private Transaction transaction;

    @Builder
    public UserRecentTransaction(Long userId, Transaction transaction) {
        this.userId = userId;
        this.transaction = transaction;
    }
}
