package codeping.flex.investment.application.service.domain;

import codeping.flex.investment.application.ports.in.investment.domain.RecentTransactionUseCase;
import codeping.flex.investment.application.ports.out.RecentTransactionOutPort;
import codeping.flex.investment.domain.model.RecentTransaction;
import codeping.flex.investment.domain.model.Transaction;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import codeping.flex.investment.global.common.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static codeping.flex.investment.application.mapper.RecentTransactionMapper.mapToRecentTransaction;
import static codeping.flex.investment.domain.exception.RecentTransactionErrorCode.RECENT_TRANSACTION_NOT_FOUND;

@ApplicationService
@RequiredArgsConstructor
public class RecentTransactionService implements RecentTransactionUseCase {

    private final RecentTransactionOutPort recentTransactionOutPort;

    @Override
    @Transactional
    public RecentTransaction saveRecentTransaction(Long userId, Transaction transaction) {
        RecentTransaction recentTransaction = mapToRecentTransaction(userId, transaction);
        return recentTransactionOutPort.saveRecentTransaction(recentTransaction);
    }

    @Override
    @Transactional(readOnly = true)
    public RecentTransaction getRecentTransactionByUserId(Long userId) {
        return findRecentTransactionOrThrow(userId);
    }

    @Override
    @Transactional
    public RecentTransaction updateRecentTransaction(Long userId, Transaction transaction) {
        RecentTransaction recentTransaction = findRecentTransactionOrThrow(userId);
        recentTransaction.synchronizeRecentTransaction(transaction);
        return recentTransactionOutPort.saveRecentTransaction(recentTransaction);
    }

    /**
     * userId 를 기반으로 최근 거래 내역을 조회합니다.
     *
     * @param userId user PK
     * @return 조회된 RecentTransaction 객체
     * @throws ApplicationException RECENT_TRANSACTION_NOT_FOUND 커스텀 예외 처리
     */
    private RecentTransaction findRecentTransactionOrThrow(Long userId) {
        return recentTransactionOutPort.getRecentTransactionByUserId(userId)
                .orElseThrow(() -> ApplicationException.from(RECENT_TRANSACTION_NOT_FOUND));
    }
}
