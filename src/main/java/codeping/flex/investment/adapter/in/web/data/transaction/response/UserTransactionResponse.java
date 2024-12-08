package codeping.flex.investment.adapter.in.web.data.transaction.response;

import codeping.flex.investment.domain.model.Transaction;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserTransactionResponse(

        @Schema(description = "거래 내역 id", example = "1")
        Long transactionId,

        @Schema(description = "유저 id", example = "1")
        Long userId,

        @Schema(description = "투자(매매) 정보")
        InvestmentTransactionResponse investment,

        @Schema(description = "크레딧 정보")
        CreditTransactionResponse credit,

        @Schema(description = "총 수익", example = "500000")
        BigDecimal totalProfit,

        @Schema(description = "잔고", example = "1000000")
        BigDecimal balance,

        @Schema(description = "거래 시간", example = "YYYY-MM-DD HH:MM:SS")
        LocalDateTime createdAt
) {
    public static UserTransactionResponse from(Transaction transaction) {
        return new UserTransactionResponse(
                transaction.getTransactionId(),
                transaction.getUserId(),
                transaction.getInvestment() != null ? InvestmentTransactionResponse.from(transaction.getInvestment()) : null,
                transaction.getCredit() != null ? CreditTransactionResponse.from(transaction.getCredit()) : null,
                transaction.getTotalProfit(),
                transaction.getBalance(),
                transaction.getCreatedAt()
        );
    }
}
