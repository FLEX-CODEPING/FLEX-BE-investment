package codeping.flex.investment.application.ports.in.investment.domain;

import codeping.flex.investment.adapter.in.web.data.holdstock.response.UserHoldStockResponse;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomPageRequest;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomSliceResponse;
import codeping.flex.investment.domain.constant.HoldStatus;
import codeping.flex.investment.domain.model.HoldStock;
import codeping.flex.investment.domain.model.Investment;

import java.util.Optional;

public interface HoldStockUseCase {

    /** SAVE **/
    // 보유 종목 저장
    HoldStock saveHoldStock(Long userId, String stockCode, String corpName, long quantity, Investment investment);
    HoldStock saveHoldStock(HoldStock holdStock);

    /** GET **/
    // 보유 종목 조회 - userId && stockCode
    Optional<HoldStock> getHoldStockByUserIdAndStockCode(Long userId, String stockCode);
    HoldStock getHoldStockById(Long holdStockId);
    // 보유 종목 조회 - userId && HoldStatus
    CustomSliceResponse<UserHoldStockResponse> getUserHoldStocks(Long userId, HoldStatus holdStatus, CustomPageRequest userHoldStockRequest);
    // 보유 종목 개별 조회
    UserHoldStockResponse getUserHoldStockByStockCode(Long userId, String stockCode);

    /** UPDATE **/
    // 매수 시 보유 종목 업데이트
    void updateBuyOrCreateHoldStock(Long userId, Investment investment);
}
