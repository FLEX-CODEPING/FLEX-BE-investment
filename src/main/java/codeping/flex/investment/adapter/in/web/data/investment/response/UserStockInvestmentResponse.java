package codeping.flex.investment.adapter.in.web.data.investment.response;

import codeping.flex.investment.domain.constant.InvestType;
import codeping.flex.investment.domain.model.Investment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserStockInvestmentResponse(

        @Schema(description = "거래 내역 id", example = "1")
        Long investmentId,

        @Schema(description = "투자 타입", example = "매수")
        InvestType investType,

        @Schema(description = "매매 수량", example = "10")
        int quantity,

        @Schema(description = "매매 가격", example = "70000")
        BigDecimal price,

        @Schema(description = "매매 총 금액", example = "700000")
        BigDecimal totalPrice,

        @Schema(description = "매매 시간", example = "YYYY-MM-DD HH:MM:SS")
        LocalDateTime createdAt
) {
    public static UserStockInvestmentResponse from(Investment investment) {
        return new UserStockInvestmentResponse(
                investment.getInvestmentId(),
                investment.getInvestType(),
                investment.getQuantity(),
                investment.getPrice(),
                investment.getTotalPrice(),
                investment.getCreatedAt()
        );
    }
}
