package codeping.flex.investment.application.ports.in.investment.domain;

import codeping.flex.investment.domain.model.Point;
import codeping.flex.investment.domain.model.Investment;
import codeping.flex.investment.domain.model.Transaction;

import java.math.BigDecimal;

public interface TransactionUseCase {

    Transaction saveInvestmentTransaction(Long userId, Investment investment, BigDecimal currentTotalProfit, BigDecimal currentBalance);
    Transaction savePointTransaction(Long userId, Point point, BigDecimal currentTotalProfit, BigDecimal currentBalance);
}
