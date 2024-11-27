package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.domain.model.Transaction;

public interface TransactionOutPort {

    /** SAVE **/
    Transaction saveTransaction(Transaction transaction);
}
