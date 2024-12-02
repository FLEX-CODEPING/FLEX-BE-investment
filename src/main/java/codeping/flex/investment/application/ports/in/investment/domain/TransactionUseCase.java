package codeping.flex.investment.application.ports.in.investment.domain;

import codeping.flex.investment.adapter.in.web.data.pagination.CustomPageRequest;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomSliceResponse;
import codeping.flex.investment.adapter.in.web.data.transaction.response.UserTransactionResponse;
import codeping.flex.investment.domain.model.Credit;
import codeping.flex.investment.domain.model.Investment;
import codeping.flex.investment.domain.model.Transaction;

import java.math.BigDecimal;

public interface TransactionUseCase {

    /** SAVE **/
    // 투자 내역 저장
    Transaction saveInvestmentTransaction(Long userId, Investment investment, BigDecimal currentTotalProfit, BigDecimal currentBalance);
    // 크레딧 내역 저장
    Transaction saveCreditTransaction(Long userId, Credit credit, BigDecimal currentTotalProfit, BigDecimal currentBalance);

    /** GET **/
    // 특정 유저 거래 내역 전체 조회
    CustomSliceResponse<UserTransactionResponse> getAllUserTransactions(Long userId, CustomPageRequest pageRequest);
}
