package codeping.flex.investment.application.ports.out;

public interface InvestmentAnalysisOutPort {

    /** GET **/
    // 특정 유저의 투자 내역 조회
    String getAllInvestmentsByUserId(Long userId);
}
