package codeping.flex.investment.global.common.exception;

import codeping.flex.investment.global.common.response.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private final BaseErrorCode code;

    public ApplicationException(BaseErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public ApplicationException(BaseErrorCode code, Object... args) {
        super(String.format(code.getMessage(), args));
        this.code = code;
    }

    public static ApplicationException from(BaseErrorCode code) {
        return new ApplicationException(code);
    }

    public static ApplicationException from(BaseErrorCode code, Object... args) {
        return new ApplicationException(code, args);
    }
}
