package codeping.flex.investment.adapter.out.persistence.entity;

import codeping.flex.investment.adapter.out.persistence.converter.BackTestPeriodConverter;
import codeping.flex.investment.adapter.out.persistence.entity.common.BaseTimeEntity;
import codeping.flex.investment.domain.constant.BackTestPeriodType;
import codeping.flex.investment.domain.constant.InvestType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "back_test")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BackTestEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long backTestId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String corpName;

    @Column(nullable = false)
    private String stockCode;

    @Enumerated(EnumType.STRING)
    @Column
    private InvestType investType;

    @Convert(converter = BackTestPeriodConverter.class)
    @Column
    private BackTestPeriodType periodType;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private BigDecimal result;

    @Builder
    public BackTestEntity(
            Long userId, String stockCode, String corpName, InvestType investType,
            BackTestPeriodType backTestPeriodType, LocalDate startDate, LocalDate endDate,
            Integer amount, BigDecimal totalPrice, BigDecimal result
    ) {
        this.userId = userId;
        this.stockCode = stockCode;
        this.corpName = corpName;
        this.investType = investType;
        this.periodType = backTestPeriodType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.result = result;
    }
}
