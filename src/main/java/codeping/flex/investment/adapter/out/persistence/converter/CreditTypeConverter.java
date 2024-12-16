package codeping.flex.investment.adapter.out.persistence.converter;

import codeping.flex.investment.domain.constant.CreditType;

public class CreditTypeConverter extends AbstractEnumAttributeConverter<CreditType> {

    private static final String ENUM_NAME = "크레딧 타입";

    public CreditTypeConverter() {
        super(CreditType.class, false, ENUM_NAME);
    }
}