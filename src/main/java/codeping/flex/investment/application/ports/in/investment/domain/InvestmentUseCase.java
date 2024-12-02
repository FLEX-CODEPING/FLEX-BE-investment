package codeping.flex.investment.application.ports.in.investment.domain;

import codeping.flex.investment.adapter.in.web.data.investment.request.BuyStockRequest;
import codeping.flex.investment.adapter.in.web.data.investment.request.SellStockRequest;
import codeping.flex.investment.adapter.in.web.data.investment.response.UserStockInvestmentResponse;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomPageRequest;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomSliceResponse;
import codeping.flex.investment.domain.model.Investment;

import java.math.BigDecimal;

public interface InvestmentUseCase {

    /** SAVE **/
    // 매수 정보 저장
    Investment saveBuyTypeInvestment(Long userId, BuyStockRequest buyStockRequest, BigDecimal totalPrice);
    // 매도 정보 저장
    Investment saveSellTypeInvestment(Long userId, SellStockRequest sellStockRequest, BigDecimal totalPrice, BigDecimal profit);

    /** GET **/
    // 특정 유저의 특정 종목 매매 내역 조회
    CustomSliceResponse<UserStockInvestmentResponse> getUserInvestmentsByStockCode(Long userId, String stockCode, CustomPageRequest customPageRequest);
}
