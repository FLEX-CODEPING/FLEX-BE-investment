package codeping.flex.investment.application.mapper;

import codeping.flex.investment.adapter.in.web.data.trading.request.BuyStockRequest;
import codeping.flex.investment.adapter.in.web.data.trading.request.SellStockRequest;
import codeping.flex.investment.domain.constant.InvestType;
import codeping.flex.investment.domain.model.Investment;

import java.math.BigDecimal;

public class InvestmentMapper {

    private InvestmentMapper() {
        throw new IllegalArgumentException();
    }

    public static Investment mapToInvestment(
            Long userId, String stockCode, String corpName, InvestType investType,
            int quantity, BigDecimal price, BigDecimal totalPrice, BigDecimal profit
    ) {
        return Investment.builder()
                .userId(userId)
                .stockCode(stockCode)
                .corpName(corpName)
                .investType(investType)
                .quantity(quantity)
                .price(price)
                .totalPrice(totalPrice)
                .profit(profit)
                .build();
    }
}
