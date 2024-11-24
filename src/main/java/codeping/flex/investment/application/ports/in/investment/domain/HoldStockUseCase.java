package codeping.flex.investment.application.ports.in.investment.domain;

import codeping.flex.investment.domain.model.HoldStock;
import codeping.flex.investment.domain.model.Investment;

import java.util.Optional;

public interface HoldStockUseCase {

    /** SAVE **/
    // 보유 종목 저장
    HoldStock saveHoldStock(Long userId, String stockCode, String corpName, long quantity, Investment investment);

    /** GET **/
    // 보유 종목 조회
    Optional<HoldStock> getHoldStockByUserIdAndStockCode(Long userId, String stockCode);
    HoldStock getHoldStockById(Long holdStockId);

    /** UPDATE **/
    // 매수 시 보유 종목 업데이트
    void updateBuyOrCreateHoldStock(Long userId, Investment investment);
}
