package codeping.flex.investment.application.ports.in.investment;

import codeping.flex.investment.domain.model.point.Point;
import codeping.flex.investment.domain.model.stockportfolio.Investment;
import codeping.flex.investment.domain.model.stockportfolio.Transaction;

import java.math.BigDecimal;

public interface TransactionUseCase {

    Transaction saveInvestmentTransaction(Long userId, Investment investment, BigDecimal currentTotalProfit, BigDecimal currentBalance);
    Transaction savePointTransaction(Long userId, Point point, BigDecimal currentTotalProfit, BigDecimal currentBalance);
}
