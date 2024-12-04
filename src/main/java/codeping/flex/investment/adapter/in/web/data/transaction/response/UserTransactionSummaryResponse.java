package codeping.flex.investment.adapter.in.web.data.transaction.response;

import codeping.flex.investment.domain.model.RecentTransaction;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserTransactionSummaryResponse(

        @Schema(description = "잔고(총 보유 크레딧)", example = "1000000")
        BigDecimal balance,

        @Schema(description = "총 수익", example = "500000")
        BigDecimal totalProfit,

        @Schema(description = "최근 거래 시간", example = "YYYY-MM-DD HH:MM:SS")
        LocalDateTime recentTransactionAt
) {
        public static UserTransactionSummaryResponse from(RecentTransaction recentTransaction) {
                return new UserTransactionSummaryResponse(
                        recentTransaction.getTransaction().getBalance(),
                        recentTransaction.getTransaction().getTotalProfit(),
                        recentTransaction.getCreatedAt()
                );
        }
}
