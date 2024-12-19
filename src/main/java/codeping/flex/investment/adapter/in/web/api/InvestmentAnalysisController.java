package codeping.flex.investment.adapter.in.web.api;

import codeping.flex.investment.adapter.in.web.data.investmentanalysis.response.InvestmentAnalysisResponse;
import codeping.flex.investment.application.ports.in.investment.domain.InvestmentAnalysisUseCase;
import codeping.flex.investment.global.annotation.architecture.WebAdapter;
import codeping.flex.investment.global.annotation.passport.Passport;
import codeping.flex.investment.global.annotation.passport.PassportInfo;
import codeping.flex.investment.global.annotation.swagger.ApiErrorCodes;
import codeping.flex.investment.global.common.response.ApplicationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static codeping.flex.investment.domain.exception.InvestmentAnalysisErrorCode.*;

@WebAdapter
@RestController
@RequestMapping("/api/investment-analysis")
@RequiredArgsConstructor
public class InvestmentAnalysisController {

    private final InvestmentAnalysisUseCase investmentAnalysisUseCase;

    @GetMapping
    @Operation(summary = "투자 내역 분석", description = "특정 유저의 투자 내역을 분석합니다.")
    @ApiErrorCodes(
            investmentAnalysisErrors = {PROMPT_GENERATION_FAILED, INVALID_API_RESPONSE, GET_INVESTMENT_FAILED}
    )
    public ApplicationResponse<InvestmentAnalysisResponse> analyzeInvestments(
            @Parameter(hidden = true) @Passport PassportInfo passportInfo
    ) {
        InvestmentAnalysisResponse analysisResponse = investmentAnalysisUseCase.analyzeInvestments(passportInfo.userId());
        return ApplicationResponse.onSuccess(analysisResponse);
    }
}
