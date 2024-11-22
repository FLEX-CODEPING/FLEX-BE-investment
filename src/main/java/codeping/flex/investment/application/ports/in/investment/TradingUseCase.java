package codeping.flex.investment.application.ports.in.investment;

import codeping.flex.investment.adapter.in.web.data.investment.request.BuyStockRequest;

public interface TradingUseCase {

    void buyStocks(Long userId, BuyStockRequest buyStockRequest);
}
