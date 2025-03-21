package codeping.flex.investment.domain.model;

import codeping.flex.investment.domain.model.common.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction extends BaseTime {

    private Long transactionId;
    private Long userId;
    private Investment investment;
    private Credit credit;
    private BigDecimal totalProfit;
    private BigDecimal balance;

    @Builder
    public Transaction(Long transactionId, Long userId, Investment investment, Credit credit, BigDecimal totalProfit, BigDecimal balance) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.investment = investment;
        this.credit = credit;
        this.totalProfit = totalProfit;
        this.balance = balance;
    }
}
