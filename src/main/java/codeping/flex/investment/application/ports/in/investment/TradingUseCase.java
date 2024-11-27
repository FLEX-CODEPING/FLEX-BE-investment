package codeping.flex.investment.application.ports.in.investment;

import codeping.flex.investment.adapter.in.web.data.trading.request.BuyStockRequest;
import codeping.flex.investment.adapter.in.web.data.trading.response.BuyStockResponse;

public interface TradingUseCase {

    /** 주식 매매 **/
    // 주식 매수
    BuyStockResponse buyStocks(Long userId, BuyStockRequest buyStockRequest);
}
