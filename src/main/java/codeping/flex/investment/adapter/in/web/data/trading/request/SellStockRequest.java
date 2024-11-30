package codeping.flex.investment.adapter.in.web.data.trading.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SellStockRequest(

        @Schema(description = "보유 종목 id", example = "1")
        @NotNull
        Long holdStockId,

        @Schema(description = "매도 수량", example = "2")
        @NotNull
        int quantity,

        @Schema(description = "매도 가격 (한 주당 가격)", example = "50000")
        @NotNull
        BigDecimal price,

        @Schema(description = "매도 총 금액", example = "100000")
        @NotNull
        BigDecimal totalBuyPrice,

        @Schema(description = "종목 코드", example = "005930")
        @NotBlank
        String stockCode,

        @Schema(description = "종목명", example = "삼성전자")
        @NotBlank
        String corpName
) {
}
