package codeping.flex.investment.adapter.out.persistence.entity.stockportfolio;

import codeping.flex.investment.adapter.out.persistence.entity.common.BaseTimeEntity;
import codeping.flex.investment.domain.constant.InvestType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "investment")
public class InvestmentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long investmentId;

    @Column(nullable = false)
    private Long userId;

    @Column
    private String corpName;

    @Column
    private String stockCode;

    @Comment("매수/매도 타입 (매수, 매도)")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvestType investType;

    @Comment("매매 수량")
    private int quantity;

    @Comment("매매 가격")
    private BigDecimal price;

    @Comment("매매 총 금액")
    private BigDecimal totalPrice;

    @Comment("매도 후 발생한 차익")
    private BigDecimal profit;

    @Builder
    public InvestmentEntity(
            Long userId, String stockCode, String corpName, InvestType investType, int quantity, BigDecimal price
    ) {
        this.userId = userId;
        this.stockCode = stockCode;
        this.corpName = corpName;
        this.investType = investType;
        this.quantity = quantity;
        this.price = price;
    }
}
