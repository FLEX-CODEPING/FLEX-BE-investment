package codeping.flex.investment.adapter.out.persistence.entity.transaction;

import codeping.flex.investment.adapter.out.persistence.entity.common.BaseTimeEntity;
import codeping.flex.investment.domain.constant.InvestType;
import codeping.flex.investment.adapter.out.persistence.entity.user.UserEntity;
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
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column
    private String corpName;

    @Column
    private String stockCode;

    // 매도/매수 타입 (매수, 매도)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvestType investType;

    // 매수 수량
    private int amount;

    // 매수 가격
    private BigDecimal price;

    // 매수 총 금액
    private BigDecimal totalBuyPrice;

    // 매도 후 발생한 차익
    private BigDecimal profit;

    @Builder
    public InvestmentEntity(Long userId, String stockCode, String corpName, InvestType investType, int amount, BigDecimal price) {
        this.userId = userId;
        this.stockCode = stockCode;
        this.corpName = corpName;
        this.investType = investType;
        this.amount = amount;
        this.price = price;
    }
}
