package codeping.flex.investment.adapter.out.persistence.entity;

import codeping.flex.investment.adapter.out.persistence.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
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
    public RecentTransactionEntity(Long recentTransactionId, Long userId, TransactionEntity transaction) {
        this.recentTransactionId = recentTransactionId;
        this.userId = userId;
        this.transaction = transaction;
    }

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        recentTransactionAt = LocalDateTime.now();
    }
}
