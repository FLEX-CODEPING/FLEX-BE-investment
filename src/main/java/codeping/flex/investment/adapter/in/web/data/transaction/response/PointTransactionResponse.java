package codeping.flex.investment.adapter.in.web.data.transaction.response;

import codeping.flex.investment.domain.constant.PointType;
import codeping.flex.investment.domain.model.Point;
import io.swagger.v3.oas.annotations.media.Schema;

public record PointTransactionResponse(
        
        @Schema(description = "크레딧 id", example = "1")
        Long pointId,

        @Schema(description = "크레딧", example = "1000")
        long point,

        @Schema(description = "크레딧 타입 (INVESTMENT | BLOG)", example = "INVESTMENT")
        PointType type
) {
    public static PointTransactionResponse from(Point point) {
        return new PointTransactionResponse(
                point.getPointId(),
                point.getPointAmount(),
                point.getPointType()
        );
    }
}
