package codeping.flex.investment.domain.model;

import codeping.flex.investment.domain.constant.CreditType;
import codeping.flex.investment.domain.model.common.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Credit extends BaseTime {

    private Long creditId;
    private Long userId;
    private long credits;
    private CreditType creditType;

    @Builder
    public Credit(Long creditId, Long userId, long credits, CreditType creditType) {
        this.creditId = creditId;
        this.userId = userId;
        this.credits = credits;
        this.creditType = creditType;
    }
}