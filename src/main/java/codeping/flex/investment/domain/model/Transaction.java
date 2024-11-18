package codeping.flex.investment.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction {
    private Long id;
    private Long userId;
    private Investment investment;
    private Long pointId;
    private BigDecimal totalProfit;
    private BigDecimal balance;

    @Builder
    public Transaction(Long userId, Investment investment, Long pointId, BigDecimal totalProfit, BigDecimal balance){
        this.userId = userId;
        this.investment = investment;
        this.pointId = pointId;
        this.totalProfit = totalProfit;
        this.balance = balance;
    }
}
