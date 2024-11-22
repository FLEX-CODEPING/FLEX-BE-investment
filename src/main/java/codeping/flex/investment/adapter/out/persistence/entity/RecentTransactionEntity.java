package codeping.flex.investment.adapter.out.persistence.entity;

import codeping.flex.investment.adapter.out.persistence.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "recent_transaction")
public class RecentTransactionEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recentTransactionId;

    @Column(nullable = false)
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private TransactionEntity transaction;

    @Column(nullable = false)
    private LocalDateTime recentTransactionAt;

    @Builder
    public RecentTransactionEntity(Long userId, TransactionEntity transactionEntity) {
        this.userId = userId;
        this.transaction = transactionEntity;
    }

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        recentTransactionAt = LocalDateTime.now();
    }
}
