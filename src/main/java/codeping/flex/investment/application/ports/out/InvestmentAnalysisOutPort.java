package codeping.flex.investment.application.ports.out;

public interface InvestmentAnalysisOutPort {

    /** GET **/
    String getAllInvestmentsByUserId(Long userId);
    long countInvestmentsByUserId(Long userId);
    String[] getTrend();
}
