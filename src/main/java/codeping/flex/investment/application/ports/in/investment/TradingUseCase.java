package codeping.flex.investment.application.ports.in.investment;

import codeping.flex.investment.adapter.in.web.data.investment.request.BuyStockRequest;
import codeping.flex.investment.adapter.in.web.data.investment.request.SellStockRequest;
import codeping.flex.investment.adapter.in.web.data.investment.response.BuyStockResponse;
import codeping.flex.investment.adapter.in.web.data.investment.response.SellStockResponse;

public interface TradingUseCase {

    /** 주식 매매 **/
    // 주식 매수
    BuyStockResponse buyStocks(Long userId, BuyStockRequest buyStockRequest);
    // 주식 매도
    SellStockResponse sellStocks(Long userId, SellStockRequest sellStockRequest);
}
