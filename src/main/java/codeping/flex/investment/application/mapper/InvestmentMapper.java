package codeping.flex.investment.application.mapper;

import codeping.flex.investment.adapter.in.web.data.trading.request.BuyStockRequest;
import codeping.flex.investment.domain.constant.InvestType;
import codeping.flex.investment.domain.model.Investment;

import java.math.BigDecimal;

public class InvestmentMapper {

    private InvestmentMapper() {
        throw new IllegalArgumentException();
    }

    public static Investment mapToInvestment(Long userId, InvestType investType, BuyStockRequest buyStockRequest, BigDecimal totalPrice) {
        return Investment.builder()
                .userId(userId)
                .stockCode(buyStockRequest.stockCode())
                .corpName(buyStockRequest.corpName())
                .investType(investType)
                .quantity(buyStockRequest.quantity())
                .price(buyStockRequest.price())
                .totalPrice(totalPrice)
                .profit(BigDecimal.ZERO)
                .build();
    }
}
