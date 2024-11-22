package codeping.flex.investment.application.service.domain;

import codeping.flex.investment.application.ports.in.investment.HoldStockUseCase;
import codeping.flex.investment.application.ports.out.HoldStockOutPort;
import codeping.flex.investment.domain.model.stockportfolio.HoldStock;
import codeping.flex.investment.domain.model.stockportfolio.Investment;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static codeping.flex.investment.application.mapper.HoldStockMapper.mapToHoldStock;

@ApplicationService
@RequiredArgsConstructor
public class HoldStockService implements HoldStockUseCase {

    private final HoldStockOutPort holdStockOutPort;

    @Override
    public Optional<HoldStock> getHoldStockByUserIdAndStockCode(Long userId, String stockCode) {
        return holdStockOutPort.getHoldStockByUserIdAndStockCode(userId, stockCode);
    }

    @Override
    public HoldStock saveHoldStock(Long userId, String stockCode, String corpName, long quantity, Investment investment) {
        HoldStock holdStock = mapToHoldStock(userId, stockCode, corpName, quantity, investment);
        return holdStockOutPort.saveHoldStock(holdStock);
    }
}
