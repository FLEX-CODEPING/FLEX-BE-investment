package codeping.flex.investment.adapter.in.web.data.holdstock.request;

import codeping.flex.investment.adapter.in.web.data.pagination.CustomPageRequest;
import codeping.flex.investment.domain.constant.HoldStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UserHoldStockRequest(

        CustomPageRequest customPageRequest,

        @Schema(description = "보유 상태 (HOLDING | SOLD)", example = "HOLDING")
        @NotNull
        HoldStatus holdStatus
) {
}
