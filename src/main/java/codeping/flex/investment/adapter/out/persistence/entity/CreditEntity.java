package codeping.flex.investment.adapter.out.persistence.entity;

import codeping.flex.investment.adapter.out.persistence.converter.CreditTypeConverter;
import codeping.flex.investment.adapter.out.persistence.entity.common.BaseTimeEntity;
import codeping.flex.investment.domain.constant.CreditType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "credit")
public class CreditEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long creditId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private long credits;

    @Convert(converter = CreditTypeConverter.class)
    @Column(nullable = false)
    private CreditType creditType;

    @Builder
    public CreditEntity(Long creditId, Long userId, long credits, CreditType creditType) {
        this.creditId = creditId;
        this.userId = userId;
        this.credits = credits;
        this.creditType = creditType;
    }
}
