package codeping.flex.investment.domain.exception;

import codeping.flex.investment.global.common.response.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RecentTransactionErrorCode implements BaseErrorCode {

    RECENT_TRANSACTION_NOT_FOUND(HttpStatus.NOT_FOUND, "RECENT_TRANSACTION_001", "회원의 최근 거래 내역이 존재하지 않습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;
}
