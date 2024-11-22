package codeping.flex.investment.application.service.domain;

import codeping.flex.investment.application.ports.in.investment.domain.TransactionUseCase;
import codeping.flex.investment.application.ports.out.TransactionOutPort;
import codeping.flex.investment.domain.model.Point;
import codeping.flex.investment.domain.model.Investment;
import codeping.flex.investment.domain.model.Transaction;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static codeping.flex.investment.application.mapper.TransactionMapper.*;

@ApplicationService
@RequiredArgsConstructor
public class TransactionService implements TransactionUseCase {

    private final TransactionOutPort transactionOutPort;

    @Override
    public Transaction saveInvestmentTransaction(Long userId, Investment investment, BigDecimal currentTotalProfit, BigDecimal currentBalance) {
        Transaction transaction = mapToInvestmentTransaction(userId, investment, currentTotalProfit, currentBalance);
        return transactionOutPort.saveTransaction(transaction);
    }

    @Override
    public Transaction savePointTransaction(Long userId, Point point, BigDecimal currentTotalProfit, BigDecimal currentBalance) {
        Transaction transaction = mapToPointTransaction(userId, point, currentTotalProfit, currentBalance);
        return transactionOutPort.saveTransaction(transaction);
    }
}
