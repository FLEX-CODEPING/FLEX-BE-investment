package codeping.flex.investment.application.mapper;

import codeping.flex.investment.domain.model.Credit;
import codeping.flex.investment.domain.model.Investment;
import codeping.flex.investment.domain.model.Transaction;

import java.math.BigDecimal;

public class TransactionMapper {

    private TransactionMapper() {
        throw new IllegalArgumentException();
    }

    /**
     * 매매에 대한 거래 내역을 저장합니다.
     */
    public static Transaction mapToInvestmentTransaction(Long userId, Investment investment, BigDecimal currentTotalProfit, BigDecimal currentBalance) {
        return Transaction.builder()
                .userId(userId)
                .investment(investment)
                .totalProfit(currentTotalProfit)
                .balance(currentBalance)
                .build();
    }

    /**
     * 크레딧 적립 또는 차감에 대한 거래 내역을 생성합니다.
     * totalProfit 은 그대로 유지하며, balance 에서 크레딧을 적립 또는 차감합니다.
     */
    public static Transaction mapToCreditTransaction(Long userId, Credit credit, BigDecimal currentTotalProfit, BigDecimal currentBalance) {
        return Transaction.builder()
                .userId(userId)
                .credit(credit)
                .totalProfit(currentTotalProfit)
                .balance(currentBalance.add(BigDecimal.valueOf(credit.getCredits())))
                .build();
    }
}
