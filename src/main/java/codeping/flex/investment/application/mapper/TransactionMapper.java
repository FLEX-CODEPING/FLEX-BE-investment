package codeping.flex.investment.application.mapper;

import codeping.flex.investment.domain.model.point.Point;
import codeping.flex.investment.domain.model.stockportfolio.Investment;
import codeping.flex.investment.domain.model.stockportfolio.Transaction;

import java.math.BigDecimal;

public class TransactionMapper {

    private TransactionMapper() {
        throw new IllegalArgumentException();
    }

    public static Transaction mapToInvestmentTransaction(Long userId, Investment investment, BigDecimal totalProfit, BigDecimal balance) {
        return Transaction.builder()
                .userId(userId)
                .investment(investment)
                .totalProfit(totalProfit)
                .balance(balance)
                .build();
    }

    public static Transaction mapToPointTransaction(Long userId, Point point, BigDecimal balance) {
        return Transaction.builder()
                .userId(userId)
                .pointId(point.getPointId())
                .balance(balance)
                .build();
    }
}
