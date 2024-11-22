package codeping.flex.investment.application.ports.in.investment;

import codeping.flex.investment.adapter.in.web.data.investment.request.BuyStockRequest;
import codeping.flex.investment.adapter.in.web.data.investment.response.BuyStockResponse;

public interface TradingUseCase {

    BuyStockResponse buyStocks(Long userId, BuyStockRequest buyStockRequest);
}
