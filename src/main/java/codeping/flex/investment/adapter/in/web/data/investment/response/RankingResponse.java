package codeping.flex.investment.adapter.in.web.data.investment.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record RankingResponse(

        @Schema(description = "유저 id", example = "1")
        Long userId,

        @Schema(description = "닉네임", example = "nickname")
        String nickname,

        @Schema(description = "블로그명", example = "blog")
        String blogName,

        @Schema(description = "프로필 이미지 url", example = "https://url.png")
        String profileImageUrl,

        @Schema(description = "총 수익", example = "500000")
        BigDecimal totalProfit
) {
}
