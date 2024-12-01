package codeping.flex.investment.domain.constant;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CreditType implements CodedEnum {

    INVESTMENT("0", "활동"),
    BLOG("1", "블로그"),
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
