package codeping.flex.investment.adapter.in.web.data.holdstock.response;

import codeping.flex.investment.domain.constant.HoldStatus;
import codeping.flex.investment.domain.model.HoldStock;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserHoldStockResponse(

        @Schema(description = "유저 id", example = "1")
        Long userId,

        @Schema(description = "보유 종목 id", example = "1")
        Long holdStockId,

        @Schema(description = "종목명", example = "삼성전자")
        String corpName,

        @Schema(description = "종목 코드", example = "005930")
        String stockCode,

        @Schema(description = "총 보유 수량", example = "10")
        long totalHoldings,

        @Schema(description = "보유 상태 (HOLDING | SOLD)", example = "HOLDING")
        HoldStatus holdStatus,

        @Schema(description = "평단가", example = "100000")
        BigDecimal avgPrice,

        @Schema(description = "원금", example = "1000000")
        BigDecimal principal,

        @Schema(description = "종목 코드", example = "005930")
        LocalDateTime createdAt
) {
    public static UserHoldStockResponse from(final HoldStock holdStock) {
        return new UserHoldStockResponse(
                holdStock.getUserId(),
                holdStock.getHoldStockId(),
                holdStock.getCorpName(),
                holdStock.getStockCode(),
                holdStock.getTotalHoldings(),
                holdStock.getHoldStatus(),
                holdStock.getAvgPrice(),
                holdStock.getPrincipal(),
                holdStock.getCreatedAt()
        );
    }
}

