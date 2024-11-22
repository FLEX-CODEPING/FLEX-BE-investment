package codeping.flex.investment.application.ports.in.investment;

import codeping.flex.investment.adapter.in.web.data.investment.request.BuyStockRequest;
import codeping.flex.investment.domain.model.stockportfolio.Investment;

public interface InvestmentUseCase {

    Investment saveBuyTypeInvestment(Long userId, BuyStockRequest buyStockRequest);
}
