package codeping.flex.investment.adapter.in.web.data.transaction.response;

import codeping.flex.investment.domain.constant.CreditType;
import codeping.flex.investment.domain.model.Credit;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreditTransactionResponse(
        
        @Schema(description = "크레딧 id", example = "1")
        Long creditId,

        @Schema(description = "크레딧", example = "1000")
        long credits,

        @Schema(description = "크레딧 타입 (INVESTMENT | BLOG)", example = "INVESTMENT")
        CreditType creditType
) {
    public static CreditTransactionResponse from(Credit credit) {
        return new CreditTransactionResponse(
                credit.getCreditId(),
                credit.getCredits(),
                credit.getCreditType()
        );
    }
}
