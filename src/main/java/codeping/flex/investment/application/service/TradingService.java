package codeping.flex.investment.application.service;

import codeping.flex.investment.adapter.in.web.data.trading.request.BuyStockRequest;
import codeping.flex.investment.adapter.in.web.data.trading.response.BuyStockResponse;
import codeping.flex.investment.application.ports.in.investment.TradingUseCase;
import codeping.flex.investment.application.ports.in.investment.domain.HoldStockUseCase;
import codeping.flex.investment.application.ports.in.investment.domain.InvestmentUseCase;
import codeping.flex.investment.application.ports.in.investment.domain.RecentTransactionUseCase;
import codeping.flex.investment.application.ports.in.investment.domain.TransactionUseCase;
import codeping.flex.investment.domain.model.HoldStock;
import codeping.flex.investment.domain.model.Investment;
import codeping.flex.investment.domain.model.RecentTransaction;
import codeping.flex.investment.domain.model.Transaction;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import codeping.flex.investment.global.common.exception.ApplicationException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

import static codeping.flex.investment.application.mapper.TradingResponseMapper.mapToBuyStockResponse;
import static codeping.flex.investment.domain.exception.InvestmentErrorCode.BALANCE_NOT_SUFFICIENT;

@ApplicationService
@RequiredArgsConstructor
public class TradingService implements TradingUseCase {

    private final InvestmentUseCase investmentUseCase;
    private final TransactionUseCase transactionUseCase;
    private final RecentTransactionUseCase recentTransactionUseCase;
    private final HoldStockUseCase holdStockUseCase;

    @Override
    public BuyStockResponse buyStocks(Long userId, BuyStockRequest buyStockRequest) {
        // 최신 거래 내역 및 잔고 조회
        RecentTransaction recentTransaction = recentTransactionUseCase.getRecentTransactionByUserId(userId);
        BigDecimal currentBalance = recentTransaction.getTransaction().getBalance();
        BigDecimal currentTotalProfit = recentTransaction.getTransaction().getTotalProfit();

        // 잔액 검증
        BigDecimal totalPrice = buyStockRequest.price().multiply(BigDecimal.valueOf(buyStockRequest.quantity()));
        validateSufficientBalance(currentBalance, totalPrice);

        // 매수 내역 저장
        Transaction transaction = saveInvestmentAndTransaction(userId, buyStockRequest, currentBalance, currentTotalProfit, totalPrice);

        // 보유 주식 업데이트
        updateHoldStocks(userId, buyStockRequest, transaction.getInvestment());

        // 최신 거래내역 업데이트
        recentTransactionUseCase.updateRecentTransaction(userId, transaction);

        return mapToBuyStockResponse(transaction.getInvestment(), transaction.getBalance());
    }

    /**
     * 현재 잔고가 매수 금액 이상인지 검증합니다.
     * 잔고가 부족하면 BALANCE_NOT_SUFFICIENT 예외를 발생시킵니다.
     *
     * @param currentBalance 현재 사용자 잔고
     * @param totalPrice 매수 총 금액
     */
    private void validateSufficientBalance(BigDecimal currentBalance, BigDecimal totalPrice) {
        if (currentBalance.compareTo(totalPrice) < 0) {
            throw ApplicationException.from(BALANCE_NOT_SUFFICIENT);
        }
    }

    /**
     * 매수 투자 정보와 거래 내역을 저장합니다.
     *
     * @param userId user PK
     * @param buyStockRequest 매수 Request Data
     * @param currentBalance 현재 잔고
     * @param currentTotalProfit 현재 총 수익
     * @return Transaction 객체
     */
    private Transaction saveInvestmentAndTransaction(
            Long userId, BuyStockRequest buyStockRequest, BigDecimal currentBalance, BigDecimal currentTotalProfit, BigDecimal totalPrice
    ) {
        Investment investment = investmentUseCase.saveBuyTypeInvestment(userId, buyStockRequest, totalPrice);
        return transactionUseCase.saveInvestmentTransaction(userId, investment, currentTotalProfit, currentBalance);
    }

    /**
     * user 가 이미 보유한 종목이라면 보유 수량을 업데이트합니다.
     * user 가 보유하고 있지 않은 종목이라면 신규 저장합니다.
     *
     * @param userId user PK
     * @param buyStockRequest 매수 Request Data
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
