package codeping.flex.investment.domain.exception;

import codeping.flex.investment.global.common.response.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum InvestmentErrorCode implements BaseErrorCode {

    BALANCE_NOT_SUFFICIENT(HttpStatus.BAD_REQUEST, "INVESTMENT_001", "잔고가 부족합니다."),
    ;

    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;
}
