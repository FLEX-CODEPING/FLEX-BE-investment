package codeping.flex.investment.application.mapper;

import codeping.flex.investment.adapter.in.web.data.investment.response.BuyStockResponse;
import codeping.flex.investment.adapter.in.web.data.investment.response.SellStockResponse;
import codeping.flex.investment.domain.model.Investment;

import java.math.BigDecimal;

public class TradingResponseMapper {

    private TradingResponseMapper() {
        throw new IllegalArgumentException();
    }

    public static BuyStockResponse mapToBuyStockResponse(Investment investment, BigDecimal currentBalance) {
        return new BuyStockResponse(
                investment.getStockCode(),
                investment.getCorpName(),
                investment.getQuantity(),
                investment.getPrice(),
                investment.getTotalPrice(),
                currentBalance,
                investment.getCreatedAt()
        );
    }

    public static SellStockResponse mapToSellStockResponse(Investment investment, BigDecimal currentBalance) {
        return new SellStockResponse(
                investment.getStockCode(),
                investment.getCorpName(),
                investment.getQuantity(),
                investment.getPrice(),
                investment.getTotalPrice(),
                investment.getProfit(),
                currentBalance,
                investment.getCreatedAt()
        );
    }
}
