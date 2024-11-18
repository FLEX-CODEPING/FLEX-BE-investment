package codeping.flex.investment.domain.model;

import codeping.flex.investment.domain.constant.InvestType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Investment {
    private Long id;
    private Long userId;
    private StockInfo stockInfo;
    private InvestType investType;
    private int amount;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private BigDecimal profit; // 매매 차익

    @Builder
    public Investment(Long userId, StockInfo stockInfo, InvestType investType, int amount, BigDecimal price) {
        this.userId = userId;
        this.stockInfo = stockInfo;
        this.investType = investType;
        this.amount = amount;
        this.price = price;
        this.totalPrice = price.multiply(new BigDecimal(amount));
        this.profit = BigDecimal.ZERO;
    }
}
