package codeping.flex.investment.domain.model;

import codeping.flex.investment.domain.model.common.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction extends BaseTime {

    private Long transactionId;
    private Long userId;
    private Investment investment;
    private Point point;
    private BigDecimal totalProfit;
    private BigDecimal balance;

    @Builder
    public Transaction(Long userId, Investment investment, Point point, BigDecimal totalProfit, BigDecimal balance){
        this.userId = userId;
        this.investment = investment;
        this.point = point;
        this.totalProfit = totalProfit;
        this.balance = balance;
    }
}
