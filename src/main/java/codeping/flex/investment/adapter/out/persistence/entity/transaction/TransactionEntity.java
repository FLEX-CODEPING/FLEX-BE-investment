package codeping.flex.investment.adapter.out.persistence.entity.transaction;

import codeping.flex.investment.adapter.out.persistence.entity.common.BaseTimeEntity;
import codeping.flex.investment.adapter.out.persistence.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "transaction")
@DynamicInsert
public class TransactionEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investment_id")
    private InvestmentEntity investment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id")
    private PointEntity point;

    @Comment("총 수익")
    @Column(nullable = false)
    private BigDecimal totalProfit;

    @Comment("잔고")
    @Column(nullable = false)
    private BigDecimal balance;

    @Builder
    public TransactionEntity(Long userId, InvestmentEntity investmentEntity, PointEntity pointEntity, BigDecimal totalProfit, BigDecimal balance) {
        this.userId = userId;
        this.investment = investmentEntity;
        this.point = pointEntity;
        this.totalProfit = totalProfit;
        this.balance = balance;
    }
}
