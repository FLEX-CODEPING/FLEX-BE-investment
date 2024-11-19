package codeping.flex.investment.domain.constant;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BackTestPeriodType implements CodedEnum{
    DAY("D","일"),
    WEEK("W", "주"),
    MONTH("M", "월"),
    YEAR("Y", "년")
    ;

    private final String code;
    private final String type;

    @Override
    public String getCode() {
        return this.code;
    }

    public String getType() {
        return this.type;
    }
}
