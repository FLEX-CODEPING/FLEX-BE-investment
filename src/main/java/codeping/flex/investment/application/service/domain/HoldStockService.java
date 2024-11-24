package codeping.flex.investment.application.service.domain;

import codeping.flex.investment.application.ports.in.investment.domain.HoldStockUseCase;
import codeping.flex.investment.application.ports.out.HoldStockOutPort;
import codeping.flex.investment.domain.constant.HoldStatus;
import codeping.flex.investment.domain.model.HoldStock;
import codeping.flex.investment.domain.model.Investment;
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
        HoldStock holdStock = mapToHoldStock(userId, stockCode, corpName, quantity, HoldStatus.HOLDING, investment);
        return holdStockOutPort.saveHoldStock(holdStock);
    }

    /**
     * user 가 이미 보유한 종목이라면 보유 수량, 상태, 평단가를 업데이트합니다.
     * user 가 보유하고 있지 않은 종목이라면 신규 저장합니다.
     *
     * @param userId user PK
     * @param investment 매수 정보
     */
    @Override
    public void updateBuyOrCreateHoldStock(Long userId, Investment investment) {
        Optional<HoldStock> holdStock = getHoldStockByUserIdAndStockCode(userId, investment.getStockCode());

        holdStock.ifPresentOrElse(
                stock -> stock.buy(investment.getQuantity(), investment.getTotalPrice(), investment),
                () -> saveHoldStock(userId, investment.getStockCode(), investment.getCorpName(), investment.getQuantity(), investment)
        );
    }
}
