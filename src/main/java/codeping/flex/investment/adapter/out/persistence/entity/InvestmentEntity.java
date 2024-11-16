package codeping.flex.investment.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "investment")
public class InvestmentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long investmentId;

    private Long userId;

    private String stockName;

    private String stockCode;

    private String investType;

    private int amount;

    private BigDecimal price;

    private BigDecimal totalPrice;

    @Builder
    public InvestmentEntity(Long userId, String stockName, String stockCode, String investType, int amount, BigDecimal price) {
        this.userId = userId;
        this.stockName = stockName;
        this.stockCode = stockCode;
        this.investType = investType;
        this.amount = amount;
        this.price = price;
    }
}
