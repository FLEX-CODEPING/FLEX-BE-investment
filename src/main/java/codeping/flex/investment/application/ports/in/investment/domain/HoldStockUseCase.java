package codeping.flex.investment.application.ports.in.investment.domain;

import codeping.flex.investment.domain.model.HoldStock;
import codeping.flex.investment.domain.model.Investment;

import java.util.Optional;

public interface HoldStockUseCase {

    Optional<HoldStock> getHoldStockByUserIdAndStockCode(Long userId, String stockCode);
    HoldStock saveHoldStock(Long userId, String stockCode, String corpName, long quantity, Investment investment);
}
