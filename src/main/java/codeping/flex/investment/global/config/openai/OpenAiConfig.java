package codeping.flex.investment.global.config.openai;

import codeping.flex.investment.adapter.in.web.data.investmentanalysis.response.InvestmentAnalysisResponse;
import codeping.flex.investment.application.openai.data.InvestmentAnalysis;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {

    @Bean
    public BeanOutputConverter<InvestmentAnalysis> inputConverter() {
        return new BeanOutputConverter<InvestmentAnalysis>(InvestmentAnalysis.class) {};
    }

    @Bean
    public BeanOutputConverter<InvestmentAnalysisResponse> outputConverter() {
        return new BeanOutputConverter<InvestmentAnalysisResponse>(InvestmentAnalysisResponse.class) {};
    }
}
