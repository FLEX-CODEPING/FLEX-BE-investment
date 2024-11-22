package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.domain.model.stockportfolio.Transaction;

public interface TransactionOutPort {

    Transaction saveTransaction(Transaction transaction);
}
