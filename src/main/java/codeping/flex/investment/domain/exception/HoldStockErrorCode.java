package codeping.flex.investment.domain.exception;

import codeping.flex.investment.global.common.response.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HoldStockErrorCode implements BaseErrorCode {

    HOLD_STOCK_NOT_FOUND(HttpStatus.NOT_FOUND, "HOLD_STOCK_001", "보유 종목을 찾을 수 없습니다."),
    HOLD_STOCK_NOT_EXIST(HttpStatus.NOT_FOUND, "HOLD_STOCK_002", "해당 종목의 보유 주식이 존재하지 않습니다."),
    HOLD_STOCK_NOT_SUFFICIENT(HttpStatus.NOT_FOUND, "HOLD_STOCK_003", "매도 수량이 보유 수량을 초과했습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;
}
