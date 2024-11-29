package codeping.flex.investment.adapter.in.web.data.investment.request;

import codeping.flex.investment.adapter.in.web.data.pagination.CustomPageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UserStockInvestmentRequest(

        CustomPageRequest customPageRequest,

        @Schema(description = "종목 코드", example = "005930")
        @NotBlank
        String stockCode
) {
}
