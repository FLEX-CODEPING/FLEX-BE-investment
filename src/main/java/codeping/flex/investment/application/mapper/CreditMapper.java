package codeping.flex.investment.application.mapper;

import codeping.flex.investment.domain.constant.CreditType;
import codeping.flex.investment.domain.model.Credit;

public class CreditMapper {

    private CreditMapper() {
        throw new IllegalArgumentException();
    }

    public static Credit mapToCredit(Long userId, long credits, CreditType creditType) {
        return Credit.builder()
                .userId(userId)
                .credits(credits)
                .creditType(creditType)
                .build();
    }
}
