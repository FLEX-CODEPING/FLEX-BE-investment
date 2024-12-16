package codeping.flex.investment.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CreditType implements CodedEnum {

    SIGNUP("1", "회원가입"),
    INITIAL_POST("2", "첫 게시물"),
    ;

    private final String code;

    @Override
    public String getCode() {
        return this.code;
    }

    @Getter
    private final String type;
}
