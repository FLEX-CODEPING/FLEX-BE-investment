package codeping.flex.investment.application.service;

import codeping.flex.investment.adapter.in.web.data.investment.request.BuyStockRequest;
import codeping.flex.investment.adapter.in.web.data.investment.request.SellStockRequest;
import codeping.flex.investment.adapter.in.web.data.investment.response.BuyStockResponse;
import codeping.flex.investment.adapter.in.web.data.investment.response.SellStockResponse;
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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static codeping.flex.investment.application.mapper.TradingResponseMapper.mapToBuyStockResponse;
import static codeping.flex.investment.application.mapper.TradingResponseMapper.mapToSellStockResponse;
import static codeping.flex.investment.domain.exception.HoldStockErrorCode.HOLD_STOCK_NOT_EXIST;
import static codeping.flex.investment.domain.exception.HoldStockErrorCode.HOLD_STOCK_NOT_SUFFICIENT;
import static codeping.flex.investment.domain.exception.InvestmentErrorCode.BALANCE_NOT_SUFFICIENT;

@ApplicationService
@RequiredArgsConstructor
@Transactional
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
        holdStockUseCase.updateBuyOrCreateHoldStock(userId, transaction.getInvestment());

        // 최신 거래내역 업데이트
        recentTransactionUseCase.updateRecentTransaction(userId, transaction);

        return mapToBuyStockResponse(transaction.getInvestment(), transaction.getBalance());
    }

    @Override
    public SellStockResponse sellStocks(Long userId, SellStockRequest sellStockRequest) {
        // 최신 거래 내역 및 잔고 조회
        RecentTransaction recentTransaction = recentTransactionUseCase.getRecentTransactionByUserId(userId);
        BigDecimal currentBalance = recentTransaction.getTransaction().getBalance();
        BigDecimal currentTotalProfit = recentTransaction.getTransaction().getTotalProfit();

        // 보유 주식 조회 및 검증
        HoldStock holdStock = holdStockUseCase.getHoldStockByUserIdAndStockCode(userId, sellStockRequest.stockCode())
                .orElseThrow(() -> ApplicationException.from(HOLD_STOCK_NOT_EXIST));
        validateSufficientHoldings(holdStock, sellStockRequest.quantity());

        // 매도 금액 및 수익 계산
        BigDecimal totalSellPrice = sellStockRequest.price().multiply(BigDecimal.valueOf(sellStockRequest.quantity()));
        BigDecimal profit = holdStock.calculateSellProfit(sellStockRequest.quantity(), sellStockRequest.price());

        // 매도 내역 저장
        Transaction transaction = saveInvestmentAndTransaction(userId, sellStockRequest, totalSellPrice, profit, currentBalance, currentTotalProfit);

        // 보유 주식 업데이트
        holdStock.sell(sellStockRequest.quantity(), transaction.getInvestment());
        holdStockUseCase.saveHoldStock(holdStock);

        // 최신 거래내역 업데이트
        recentTransactionUseCase.updateRecentTransaction(userId, transaction);

        return mapToSellStockResponse(transaction.getInvestment(), transaction.getBalance());
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
            Long userId, BuyStockRequest buyStockRequest, BigDecimal currentBalance, BigDecimal currentTotalProfit, BigDecimal totalBuyPrice
    ) {
        Investment investment = investmentUseCase.saveBuyTypeInvestment(userId, buyStockRequest, totalBuyPrice);
        return transactionUseCase.saveInvestmentTransaction(userId, investment, currentTotalProfit, currentBalance.subtract(totalBuyPrice));
    }

    /**
     * 매도 수량이 보유 수량을 초과했는지 검증합니다.
     *
     * @param holdStock 보유 주식
     * @param sellQuantity 매도 수량
     */
    private void validateSufficientHoldings(HoldStock holdStock, long sellQuantity) {
        if (!holdStock.isHoldingsSufficient(sellQuantity)) {
            throw ApplicationException.from(HOLD_STOCK_NOT_SUFFICIENT);
        }
    }

    /**
     * 매도 투자 정보와 거래 내역을 저장합니다.
     *
     * @param userId user PK
     * @param sellStockRequest 매도 Request Data
     * @param totalSellPrice 총 매도 금액
     * @param profit 매도 수익
     * @param currentBalance 현재 잔고
     * @param currentTotalProfit 현재 총 수익
     * @return 저장된 Transaction 객체
     */
    private Transaction saveInvestmentAndTransaction(
            Long userId, SellStockRequest sellStockRequest, BigDecimal totalSellPrice, BigDecimal profit,
            BigDecimal currentBalance, BigDecimal currentTotalProfit
    ) {
        Investment investment = investmentUseCase.saveSellTypeInvestment(userId, sellStockRequest, totalSellPrice, profit);
        return transactionUseCase.saveInvestmentTransaction(userId, investment, currentTotalProfit.add(profit), currentBalance.add(totalSellPrice));
    }
}
