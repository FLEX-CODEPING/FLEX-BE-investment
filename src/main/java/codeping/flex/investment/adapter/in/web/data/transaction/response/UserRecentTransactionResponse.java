package codeping.flex.investment.adapter.in.web.data.transaction.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserRecentTransactionResponse(

        @Schema(description = "잔고", example = "1000000")
        BigDecimal balance,

        @Schema(description = "총 수익", example = "500000")
        BigDecimal totalProfit,

        @Schema(description = "최근 거래 시간", example = "YYYY-MM-DD HH:MM:SS")
        LocalDateTime recentTransactionAt
) {
}
