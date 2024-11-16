package codeping.flex.investment.domain.exception;

import codeping.flex.investment.global.common.response.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum InvestmentErrorCode implements BaseErrorCode {

    ;

    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;
}
