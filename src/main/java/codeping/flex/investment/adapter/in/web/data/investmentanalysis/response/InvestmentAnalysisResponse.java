package codeping.flex.investment.adapter.in.web.data.investmentanalysis.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record InvestmentAnalysisResponse (

    @Schema(description = "사용자의 투자 성향")
    InvestmentStyle investmentStyle,

    @Schema(description = "추천 투자 전략")
    InvestmentStrategy investmentStrategy
) {
    public record InvestmentStyle(
            @Schema(description = "투자 위험 수준", example = "중")
            String riskLevel,
            @Schema(description = "거래 패턴", example = "분할 매수 후 목표가 도달 시 매도하시는 안정 추구형 투자를 하고 계십니다.")
            String tradingPattern,
            @Schema(description = "투자 분석", example = "월 평균 2-3회 거래하시며 시가총액 상위 종목을 선호하십니다. 손실 구간에서는 추가 매수로 평단가를 조정하시고, 수익실현은 10-15% 구간에서 진행하시는 편입니다.")
            String analysis
    ) {}

    public record InvestmentStrategy(
            @Schema(description = "투자 추천", example = "현재의 분할 매수 전략을 유지하시되, 업종 다각화가 필요해 보입니다. 성장성과 안정성을 겸비한 중형주 비중 확대를 고려해보세요.")
            String recommendation,
            @Schema(description = "리스크 관리", example = "단일 종목 투자 비중을 20% 이하로 제한하시고, 스탑로스를 7% 정도로 설정하시기를 권장드립니다. 현금 비중은 20% 이상 유지하시는 것이 좋겠습니다.")
            String riskManagement,
            @Schema(description = "전략 분석", example = "최근 거래 패턴을 분석한 결과 특정 섹터에 집중되어 있습니다. 섹터별 위험 분산과 리스크 관리 강화가 필요해 보입니다.")
            String analysis
    ) {}
}
