package codeping.flex.investment.application.openai.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InvestmentAnalysis(
        @JsonProperty(required = true, value = "investmentStyle") InvestmentStyle investmentStyle,
        @JsonProperty(required = true, value = "investmentStrategy") InvestmentStrategy investmentStrategy
) {

        record InvestmentStyle(
                @JsonProperty(required = true, value = "riskLevel") String riskLevel,
                @JsonProperty(required = true, value = "tradingPattern") String tradingPattern,
                @JsonProperty(required = true, value = "analysis") String analysis) {}

        record InvestmentStrategy(
                @JsonProperty(required = true, value = "recommendation") String recommendation,
                @JsonProperty(required = true, value = "riskManagement") String riskManagement,
                @JsonProperty(required = true, value = "analysis") String analysis) {}
}
