package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.domain.model.HoldStock;

import java.util.Optional;

public interface HoldStockOutPort {

    /** SAVE **/
    HoldStock saveHoldStock(HoldStock holdStock);

    /** GET **/
    Optional<HoldStock> getHoldStockByUserIdAndStockCode(Long userId, String stockCode);
    Optional<HoldStock> getHoldStockById(Long holdStockId);
}
