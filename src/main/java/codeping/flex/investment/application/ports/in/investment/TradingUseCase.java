package codeping.flex.investment.application.ports.in.investment;

import codeping.flex.investment.adapter.in.web.data.investment.request.BuyStockRequest;
import codeping.flex.investment.adapter.in.web.data.investment.request.SellStockRequest;

public interface TradingUseCase {

    Long buyStocks(BuyStockRequest buyStockRequest);
    Long sellStocks(SellStockRequest sellStockRequest);
}
