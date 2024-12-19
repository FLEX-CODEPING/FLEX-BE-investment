package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.adapter.in.web.data.investment.response.RankingResponse;
import codeping.flex.investment.domain.model.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface TransactionOutPort {

    /** SAVE **/
    Transaction saveTransaction(Transaction transaction);

    /** GET **/
    Slice<Transaction> getAllTransactionsByUserId(final Long userId, Pageable pageable);
    List<RankingResponse> getRankings(Pageable pageable);
}
