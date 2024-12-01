package codeping.flex.investment.domain.model;

import codeping.flex.investment.domain.constant.CreditType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Credit {

    private Long creditId;
    private Long userId;
    private long credits;
    private CreditType creditType;

    @Builder
    public Credit(Long userId, long credits, CreditType creditType) {
        this.userId = userId;
        this.credits = credits;
        this.creditType = creditType;
    }

    public void addPoint(long amount) {
        this.credits += amount;
    }
}