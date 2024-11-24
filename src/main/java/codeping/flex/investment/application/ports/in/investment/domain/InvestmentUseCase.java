package codeping.flex.investment.application.ports.in.investment.domain;

import codeping.flex.investment.adapter.in.web.data.trading.request.BuyStockRequest;
import codeping.flex.investment.adapter.in.web.data.trading.request.SellStockRequest;
import codeping.flex.investment.domain.model.Investment;

import java.math.BigDecimal;

public interface InvestmentUseCase {

    /** SAVE **/
    // 매수 정보 저장
    Investment saveBuyTypeInvestment(Long userId, BuyStockRequest buyStockRequest, BigDecimal totalPrice);
    // 매도 정보 저장
    Investment saveSellTypeInvestment(Long userId, SellStockRequest sellStockRequest, BigDecimal totalPrice, BigDecimal profit);
}
