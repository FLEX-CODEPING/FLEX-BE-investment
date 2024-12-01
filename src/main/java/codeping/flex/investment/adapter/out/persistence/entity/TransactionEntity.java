package codeping.flex.investment.adapter.out.persistence.entity;

import codeping.flex.investment.adapter.out.persistence.entity.common.BaseTimeEntity;
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
    private Long transactionId;

    @Column(nullable = false)
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investment_id")
    private InvestmentEntity investment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_id")
    private CreditEntity credit;

    @Comment("총 수익")
    @Column(nullable = false)
    private BigDecimal totalProfit;

    @Comment("잔고")
    @Column(nullable = false)
    private BigDecimal balance;

    @Builder
    public TransactionEntity(
            Long userId, InvestmentEntity investment, CreditEntity credit,
            BigDecimal totalProfit, BigDecimal balance
    ) {
        this.userId = userId;
        this.investment = investment;
        this.credit = credit;
        this.totalProfit = totalProfit;
        this.balance = balance;
    }
}
