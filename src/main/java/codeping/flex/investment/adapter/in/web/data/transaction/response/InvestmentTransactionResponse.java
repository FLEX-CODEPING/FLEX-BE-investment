package codeping.flex.investment.adapter.in.web.data.transaction.response;

import codeping.flex.investment.domain.constant.InvestType;
import codeping.flex.investment.domain.model.Investment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record InvestmentTransactionResponse(

        @Schema(description = "거래 내역 id", example = "1")
        Long investmentId,

        @Schema(description = "종목명", example = "삼성전자")
        String corpName,

        @Schema(description = "종목 코드", example = "005930")
        String stockCode,

        @Schema(description = "투자 타입", example = "매수")
        InvestType investType,

        @Schema(description = "매매 수량", example = "10")
        int quantity,

        @Schema(description = "매매 가격", example = "70000")
        BigDecimal price,

        @Schema(description = "매매 총 금액", example = "700000")
        BigDecimal totalPrice,

        @Schema(description = "차익", example = "30000")
        BigDecimal profit
) {
    public static InvestmentTransactionResponse from(Investment investment) {
        return new InvestmentTransactionResponse(
                investment.getInvestmentId(),
                investment.getCorpName(),
                investment.getStockCode(),
                investment.getInvestType(),
                investment.getQuantity(),
                investment.getPrice(),
                investment.getTotalPrice(),
                investment.getProfit()
        );
    }
}
