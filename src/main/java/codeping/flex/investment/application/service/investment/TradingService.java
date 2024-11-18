package codeping.flex.investment.application.service.investment;

import codeping.flex.investment.adapter.in.web.data.investment.request.BuyStockRequest;
import codeping.flex.investment.adapter.in.web.data.investment.request.SellStockRequest;
import codeping.flex.investment.application.ports.in.investment.TradingUseCase;
import codeping.flex.investment.application.ports.out.InvestmentOutPort;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class TradingService implements TradingUseCase {

    private final InvestmentOutPort investmentOutPort;

    @Override
    public Long buyStocks(BuyStockRequest buyStockRequest) {
        return null;
    }

    @Override
    public Long sellStocks(SellStockRequest sellStockRequest) {
        return null;
    }
}
