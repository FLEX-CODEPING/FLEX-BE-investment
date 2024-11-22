package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.domain.model.Transaction;

public interface TransactionOutPort {

    Transaction saveTransaction(Transaction transaction);
}
