package codeping.flex.investment.adapter.out.persistence.entity.transaction;

import codeping.flex.investment.adapter.out.persistence.entity.common.BaseTimeEntity;
import codeping.flex.investment.adapter.out.persistence.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_recent_transaction")
public class UserRecentTransactionEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private TransactionEntity transaction;

    @Column(nullable = false)
    private LocalDateTime recentTransactionAt;

    @Builder
    public UserRecentTransactionEntity(Long userId, TransactionEntity transactionEntity){
        this.userId = userId;
        this.transaction = transactionEntity;
    }

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        recentTransactionAt = LocalDateTime.now();
    }
}
