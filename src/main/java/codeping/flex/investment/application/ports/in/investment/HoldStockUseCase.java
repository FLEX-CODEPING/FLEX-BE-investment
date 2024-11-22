package codeping.flex.investment.application.ports.in.investment;

import codeping.flex.investment.domain.model.stockportfolio.HoldStock;
import codeping.flex.investment.domain.model.stockportfolio.Investment;

import java.util.Optional;

public interface HoldStockUseCase {

    Optional<HoldStock> getHoldStockByUserIdAndStockCode(Long userId, String stockCode);
    HoldStock saveHoldStock(Long userId, String stockCode, String corpName, long quantity, Investment investment);
}
