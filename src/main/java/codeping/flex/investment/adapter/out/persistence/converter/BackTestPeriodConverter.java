package codeping.flex.investment.adapter.out.persistence.converter;

import codeping.flex.investment.domain.constant.BackTestPeriodType;

public class BackTestPeriodConverter extends AbstractEnumAttributeConverter<BackTestPeriodType> {
    private static final String ENUM_NAME = "백테스팅 기간";

    public BackTestPeriodConverter() {
        super(BackTestPeriodType.class, false, ENUM_NAME);
    }
}
