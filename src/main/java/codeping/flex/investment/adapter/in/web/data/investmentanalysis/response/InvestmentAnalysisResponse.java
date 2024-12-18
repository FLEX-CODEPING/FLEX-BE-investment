package codeping.flex.investment.adapter.in.web.data.investmentanalysis.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record InvestmentAnalysisResponse (

    @Schema(description = "사용자의 투자 성향",
            example = "[\"1. 안정성 추구: 이 세 기업은 각 분야에서 선도적인 위치를 차지하고 있는 대기업으로, 상대적으로 안정적인 투자를 선호하는 성향을 보입니다.\", " +
                    "\"2. 글로벌 시장 관심: 세 기업 모두 글로벌 시장에서 활약하고 있어, 국제 시장 동향에 관심이 많습니다.\", " +
                    "\"3. 장기 투자 성향: 지속적인 성장과 혁신을 보여주는 기업들이므로, 단기 수익보다는 장기적인 가치 상승을 기대합니다.\"]")
    String[] investmentStyle,

    @Schema(description = "추천 투자 전략",
            example = "[\"1. 분산 투자: 세 기업에 골고루 투자하여 리스크를 분산시킵니다.\", " +
                    "\"2. 글로벌 시장 관심: 세 기업 모두 글로벌 시장에서 활약하고 있어, 국제 시장 동향에 관심이 많습니다.\", " +
                    "\"3. 장기 투자 성향: 지속적인 성장과 혁신을 보여주는 기업들이므로, 단기 수익보다는 장기적인 가치 상승을 기대합니다.\"]")
    String[] investmentStrategy,

    @Schema(description = "투자 트렌드",
            example = "[\"1. 안정성 추구: 이 세 기업은 각 분야에서 선도적인 위치를 차지하고 있는 대기업으로, 상대적으로 안정적인 투자를 선호하는 성향을 보입니다.\", " +
                    "\"2. 글로벌 시장 관심: 세 기업 모두 글로벌 시장에서 활약하고 있어, 국제 시장 동향에 관심이 많습니다.\", " +
                    "\"3. 장기 투자 성향: 지속적인 성장과 혁신을 보여주는 기업들이므로, 단기 수익보다는 장기적인 가치 상승을 기대합니다.\"]")
    String[] investmentTrend
) {
}
