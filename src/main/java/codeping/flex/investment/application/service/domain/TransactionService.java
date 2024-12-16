package codeping.flex.investment.application.service.domain;

import codeping.flex.investment.adapter.in.web.data.investment.response.RankingResponse;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomPageRequest;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomSliceResponse;
import codeping.flex.investment.adapter.in.web.data.transaction.response.UserTransactionResponse;
import codeping.flex.investment.application.ports.in.investment.domain.TransactionUseCase;
import codeping.flex.investment.application.ports.out.TransactionOutPort;
import codeping.flex.investment.domain.model.Credit;
import codeping.flex.investment.domain.model.Investment;
import codeping.flex.investment.domain.model.Transaction;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static codeping.flex.investment.application.mapper.TransactionMapper.mapToCreditTransaction;
import static codeping.flex.investment.application.mapper.TransactionMapper.mapToInvestmentTransaction;

@ApplicationService
@RequiredArgsConstructor
public class TransactionService implements TransactionUseCase {

    private final TransactionOutPort transactionOutPort;

    @Override
    @Transactional
    public Transaction saveInvestmentTransaction(Long userId, Investment investment, BigDecimal currentTotalProfit, BigDecimal currentBalance) {
        Transaction transaction = mapToInvestmentTransaction(userId, investment, currentTotalProfit, currentBalance);
        return transactionOutPort.saveTransaction(transaction);
    }

    @Override
    @Transactional
    public Transaction saveCreditTransaction(Long userId, Credit credit, BigDecimal currentTotalProfit, BigDecimal currentBalance) {
        Transaction transaction = mapToCreditTransaction(userId, credit, currentTotalProfit, currentBalance);
        return transactionOutPort.saveTransaction(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomSliceResponse<UserTransactionResponse> getAllUserTransactions(Long userId, CustomPageRequest pageRequest) {
        Slice<Transaction> transactionSlice = transactionOutPort.getAllTransactionsByUserId(userId, pageRequest.toPageRequest());

        List<UserTransactionResponse> content = transactionSlice.getContent()
                .stream()
                .map(UserTransactionResponse::from)
                .toList();

        return CustomSliceResponse.of(content, transactionSlice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RankingResponse> getRankings() {
        Pageable numOfRanks = PageRequest.of(0, 3);
        List<RankingResponse> rankings = transactionOutPort.getRankings(numOfRanks);

        AtomicInteger rankCounter = new AtomicInteger(1);
        return rankings.stream()
                .map(r -> RankingResponse.from(rankCounter.getAndIncrement(), r))
                .collect(Collectors.toList());
    }
}
