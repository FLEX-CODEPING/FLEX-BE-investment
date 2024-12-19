package codeping.flex.investment.adapter.out.persistence.entity;

import codeping.flex.investment.adapter.out.persistence.entity.common.BaseTimeEntity;
import codeping.flex.investment.domain.constant.HoldStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "hold_stock")
public class HoldStockEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holdStockId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String corpName;

    @Column(nullable = false)
    private String stockCode;

    @Comment("총 보유량")
    @Column(nullable = false)
    private long totalHoldings;

    @Comment("보유 상태")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HoldStatus holdStatus;

    @Comment("평단가")
    @Column(nullable = false)
    private BigDecimal avgPrice;

    @Comment("원금")
    @Column(nullable = false)
    private BigDecimal principal;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recent_investment_id", nullable = false)
    private InvestmentEntity recentInvestment;

    @Builder
    public HoldStockEntity(
            Long holdStockId, Long userId, String stockCode, String corpName,
            long totalHoldings, HoldStatus holdStatus, InvestmentEntity recentInvestment, BigDecimal avgPrice, BigDecimal principal
    ) {
        this.holdStockId = holdStockId;
        this.userId = userId;
        this.stockCode = stockCode;
        this.corpName = corpName;
        this.totalHoldings = totalHoldings;
        this.holdStatus = holdStatus;
        this.avgPrice = avgPrice;
        this.principal = principal;
        this.recentInvestment = recentInvestment;
    }
}
