package codeping.flex.investment.application.service.aggregate;

import codeping.flex.investment.adapter.in.web.data.investment.request.BuyStockRequest;
import codeping.flex.investment.application.ports.in.investment.*;
import codeping.flex.investment.domain.model.stockportfolio.HoldStock;
import codeping.flex.investment.domain.model.stockportfolio.Investment;
import codeping.flex.investment.domain.model.stockportfolio.RecentTransaction;
import codeping.flex.investment.domain.model.stockportfolio.Transaction;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import codeping.flex.investment.global.common.exception.ApplicationException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

import static codeping.flex.investment.domain.exception.InvestmentErrorCode.BALANCE_NOT_SUFFICIENT;

@ApplicationService
@RequiredArgsConstructor
public class TradingService implements TradingUseCase {

    private final InvestmentUseCase investmentUseCase;
    private final TransactionUseCase transactionUseCase;
    private final RecentTransactionUseCase recentTransactionUseCase;
    private final HoldStockUseCase holdStockUseCase;

    @Override
    public void buyStocks(Long userId, BuyStockRequest buyStockRequest) {
        // 최신 거래 내역 및 잔고 조회
        RecentTransaction recentTransaction = recentTransactionUseCase.getRecentTransactionByUserId(userId);
        BigDecimal currentBalance = recentTransaction.getTransaction().getBalance();
        BigDecimal currentTotalProfit = recentTransaction.getTransaction().getTotalProfit();

        // 잔액 검증
        BigDecimal totalPrice = buyStockRequest.price().multiply(BigDecimal.valueOf(buyStockRequest.quantity()));
        validateSufficientBalance(currentBalance, totalPrice);

        // 매수 내역 저장
        Transaction transaction = saveInvestmentAndTransaction(userId, buyStockRequest, currentBalance, currentTotalProfit);

        // 보유 주식 업데이트
        updateHoldStocks(userId, buyStockRequest, transaction.getInvestment());

        // 최신 거래내역 업데이트
        recentTransactionUseCase.updateRecentTransaction(userId, transaction);
    }

    /**
     * 현재 잔고와 매수 금액을 비교하여 매수 가능 여부를 검증합니다.
     *
     * @param currentBalance 현재 잔고
     * @param totalPrice 매수 금액
     */
    private void validateSufficientBalance(BigDecimal currentBalance, BigDecimal totalPrice) {
        if (currentBalance.compareTo(totalPrice) < 0) {
            throw ApplicationException.from(BALANCE_NOT_SUFFICIENT);
        }
    }

    /**
     * 매수 정보 및 거래 내역을 저장 후, 해당 매수 내역에 연관된 거래 내역을 반환합니다.
     *
     * @param userId userId
     * @param buyStockRequest 매수 Request DTO
     * @param currentBalance 현재 잔고
     * @param currentTotalProfit 현재 총 수익
     * @return Transaction 객체
     */
    private Transaction saveInvestmentAndTransaction(
            Long userId, BuyStockRequest buyStockRequest, BigDecimal currentBalance, BigDecimal currentTotalProfit
    ) {
        Investment investment = investmentUseCase.saveBuyTypeInvestment(userId, buyStockRequest);
        return transactionUseCase.saveInvestmentTransaction(userId, investment, currentTotalProfit, currentBalance);
    }

    /**
     * user 가 이미 보유한 종목이라면 매수한 수량만큼 보유 수량을 추가합니다.
     * user 가 보유하고 있지 않은 종목이라면 보유 종목으로 저장합니다.
     *
     * @param userId userId
     * @param buyStockRequest 매수 Request DTO
     * @param investment 매수 정보
     */
    private void updateHoldStocks(Long userId, BuyStockRequest buyStockRequest, Investment investment) {
        Optional<HoldStock> holdStock = holdStockUseCase.getHoldStockByUserIdAndStockCode(userId, investment.getStockCode());

        holdStock.ifPresentOrElse(
                stock -> stock.addHoldings(buyStockRequest.quantity()),
                () -> holdStockUseCase.saveHoldStock(
                        userId,
                        buyStockRequest.stockCode(),
                        buyStockRequest.corpName(),
                        buyStockRequest.quantity(),
                        investment
                )
        );
    }
}
