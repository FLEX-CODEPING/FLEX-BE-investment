package codeping.flex.investment.adapter.in.web.data.trading.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SellStockResponse(

        @Schema(description = "종목 코드", example = "005930")
        String stockCode,

        @Schema(description = "종목명", example = "삼성전자")
        String corpName,

        @Schema(description = "매도 수량", example = "2")
        int quantity,

        @Schema(description = "구매 가격 (한 주당 가격)", example = "50000")
        BigDecimal price,

        @Schema(description = "매도 총 금액", example = "100000")
        BigDecimal totalPrice,

        @Schema(description = "매도 수익", example = "5000")
        BigDecimal profit,

        @Schema(description = "매도 후 현재 잔고", example = "100000")
        BigDecimal currentBalance,

        @Schema(description = "매도 시간", example = "YYYY-MM-DD HH:MM:SS")
        LocalDateTime createdAt
) {
}
