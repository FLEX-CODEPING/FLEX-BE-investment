package codeping.flex.investment.application.mapper;

import codeping.flex.investment.domain.constant.HoldStatus;
import codeping.flex.investment.domain.model.stockportfolio.HoldStock;
import codeping.flex.investment.domain.model.stockportfolio.Investment;

public class HoldStockMapper {

    private HoldStockMapper() {
        throw new IllegalArgumentException();
    }

    public static HoldStock mapToHoldStock(Long userId, String stockCode, String corpName, long quantity, HoldStatus holdStatus, Investment investment) {
        return HoldStock.builder()
                .userId(userId)
                .stockCode(stockCode)
                .corpName(corpName)
                .totalHoldings(quantity)
                .holdStatus(holdStatus)
                .recentInvestment(investment)
                .build();
    }
}
