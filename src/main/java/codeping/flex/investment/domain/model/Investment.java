package codeping.flex.investment.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Investment {

    private Long investmentId;

    private Long userId;

    private String stockName;

    private String stockCode;

    private String investType;

    private int amount;

    private BigDecimal price;

    private BigDecimal totalPrice;

    @Builder
    public Investment(Long userId, String stockName, String stockCode, String investType, int amount, BigDecimal price) {
        this.userId = userId;
        this.stockName = stockName;
        this.stockCode = stockCode;
        this.investType = investType;
        this.amount = amount;
        this.price = price;
    }
}
