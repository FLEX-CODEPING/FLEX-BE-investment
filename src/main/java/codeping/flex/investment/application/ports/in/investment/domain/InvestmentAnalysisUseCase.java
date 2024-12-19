package codeping.flex.investment.application.ports.in.investment.domain;

import codeping.flex.investment.adapter.in.web.data.investmentanalysis.response.InvestmentAnalysisResponse;

public interface InvestmentAnalysisUseCase {
    /** GET **/
    // 특정 유저의 투자 내역 조회
    InvestmentAnalysisResponse analyzeInvestments(Long userId);
}
