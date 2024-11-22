package codeping.flex.investment.domain.model;

import codeping.flex.investment.domain.constant.BackTestPeriodType;
import codeping.flex.investment.domain.constant.InvestType;
import codeping.flex.investment.domain.model.common.BaseTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BackTest extends BaseTime {

    private Long backTestId;
    private Long userId;
    private String corpName;
    private String stockCode;
    private InvestType investType;
    private BackTestPeriodType periodType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer amount;
    private BigDecimal totalPrice;
    private BigDecimal result;
}
