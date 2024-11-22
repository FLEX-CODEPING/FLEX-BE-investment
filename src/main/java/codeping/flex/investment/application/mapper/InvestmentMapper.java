package codeping.flex.investment.application.mapper;

import codeping.flex.investment.adapter.in.web.data.investment.request.BuyStockRequest;
import codeping.flex.investment.domain.constant.InvestType;
import codeping.flex.investment.domain.model.stockportfolio.Investment;

public class InvestmentMapper {

    private InvestmentMapper() {
        throw new IllegalArgumentException();
    }

    public static Investment mapToInvestment(Long userId, InvestType investType, BuyStockRequest buyStockRequest) {
        return Investment.builder()
                .userId(userId)
                .stockCode(buyStockRequest.stockCode())
                .corpName(buyStockRequest.corpName())
                .investType(investType)
                .quantity(buyStockRequest.quantity())
                .price(buyStockRequest.price())
                .build();
    }
}
