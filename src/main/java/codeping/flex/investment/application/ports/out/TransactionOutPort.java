package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.domain.model.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface TransactionOutPort {

    /** SAVE **/
    Transaction saveTransaction(Transaction transaction);

    /** GET **/
    Slice<Transaction> getAllTransactionsByUserId(final Long userId, Pageable pageable);
}
