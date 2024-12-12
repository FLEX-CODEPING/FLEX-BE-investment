package codeping.flex.investment.domain.exception;

import codeping.flex.investment.global.common.response.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum InvestmentAnalysisErrorCode implements BaseErrorCode {

    INSUFFICIENT_INVESTMENT_DATA(HttpStatus.INTERNAL_SERVER_ERROR, "INVESTMENT_ANALYSIS_001", "최소 투자 내역 수(%d개)를 충족하지 못했습니다. 현재 투자 내역 수: %d"),
    GET_INVESTMENT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "INVESTMENT_ANALYSIS_002", "투자 내역 조회에 실패했습니다."),
    PROMPT_GENERATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "INVESTMENT_ANALYSIS_003", "AI 분석 프롬프트 생성에 실패했습니다."),
    INVALID_API_RESPONSE(HttpStatus.INTERNAL_SERVER_ERROR, "INVESTMENT_ANALYSIS_004", "AI 분석 결과 생성에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;
}
