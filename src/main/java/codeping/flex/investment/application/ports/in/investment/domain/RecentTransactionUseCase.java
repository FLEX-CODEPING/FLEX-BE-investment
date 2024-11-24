package codeping.flex.investment.application.ports.in.investment.domain;

import codeping.flex.investment.domain.model.RecentTransaction;
import codeping.flex.investment.domain.model.Transaction;

public interface RecentTransactionUseCase {

    /** SAVE **/
    // 최근 거래 내역 저장
    RecentTransaction saveRecentTransaction(Long userId, Transaction transaction);

    /** GET **/
    // 최근 거래 내역 조회
    RecentTransaction getRecentTransactionByUserId(Long userId);

    /** UPDATE **/
    // 최근 거래 내역 업데이트
    RecentTransaction updateRecentTransaction(Long userId, Transaction transaction);
}
