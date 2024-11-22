package codeping.flex.investment.application.service.domain;

import codeping.flex.investment.application.ports.in.investment.TransactionUseCase;
import codeping.flex.investment.application.ports.out.TransactionOutPort;
import codeping.flex.investment.domain.model.point.Point;
import codeping.flex.investment.domain.model.stockportfolio.Investment;
import codeping.flex.investment.domain.model.stockportfolio.Transaction;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static codeping.flex.investment.application.mapper.TransactionMapper.*;

@ApplicationService
@RequiredArgsConstructor
public class TransactionService implements TransactionUseCase {

    private final TransactionOutPort transactionOutPort;

    public Transaction saveInvestmentTransaction(
            Long userId, Investment investment, BigDecimal totalProfit, BigDecimal balance
    ) {
        Transaction transaction = mapToInvestmentTransaction(userId, investment, totalProfit, balance);
        return transactionOutPort.saveTransaction(transaction);
    }

    public Transaction savePointTransaction(Long userId, Point point, BigDecimal balance) {
        Transaction transaction = mapToPointTransaction(userId, point, balance);
        return transactionOutPort.saveTransaction(transaction);
    }
}
