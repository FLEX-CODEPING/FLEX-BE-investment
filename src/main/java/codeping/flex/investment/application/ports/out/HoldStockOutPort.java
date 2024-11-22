package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.domain.model.stockportfolio.HoldStock;

import java.util.Optional;

public interface HoldStockOutPort {

    HoldStock saveHoldStock(HoldStock holdStock);
    Optional<HoldStock> getHoldStockByUserIdAndStockCode(Long userId, String stockCode);
}
