
package codeping.flex.investment.adapter.out.persistence.entity.transaction;

import codeping.flex.investment.adapter.out.persistence.entity.common.BaseTimeEntity;
import codeping.flex.investment.domain.constant.HoldStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "hold_stock")
public class HoldStockEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToOne
    @JoinColumn(name = "recent_investment_id", nullable = false)
    private InvestmentEntity recentInvestment;

    @Builder
    public HoldStockEntity(Long userId, String stockCode, String corpName, long totalHoldings, HoldStatus holdStatus, InvestmentEntity recentInvestmentEntity) {
        this.userId = userId;
        this.stockCode = stockCode;
        this.corpName = corpName;
        this.totalHoldings = totalHoldings;
        this.holdStatus = holdStatus;
        this.recentInvestment = recentInvestmentEntity;
    }
}
