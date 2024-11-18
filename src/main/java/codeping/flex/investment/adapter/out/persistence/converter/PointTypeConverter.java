package codeping.flex.investment.adapter.out.persistence.converter;

import codeping.flex.investment.domain.constant.PointType;

public class PointTypeConverter extends AbstractEnumAttributeConverter<PointType> {
    private static final String ENUM_NAME = "포인트 적립 유형";

    public PointTypeConverter() {
        super(PointType.class, false, ENUM_NAME);
    }
}