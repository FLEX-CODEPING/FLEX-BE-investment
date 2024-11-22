package codeping.flex.investment.application.service.domain;

import codeping.flex.investment.adapter.in.web.data.investment.request.BuyStockRequest;
import codeping.flex.investment.application.ports.in.investment.InvestmentUseCase;
import codeping.flex.investment.application.ports.out.InvestmentOutPort;
import codeping.flex.investment.domain.constant.InvestType;
import codeping.flex.investment.domain.model.stockportfolio.Investment;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static codeping.flex.investment.application.mapper.InvestmentMapper.mapToInvestment;

@ApplicationService
@RequiredArgsConstructor
public class InvestmentService implements InvestmentUseCase {

    private final InvestmentOutPort investmentOutPort;

    @Override
    public Investment saveBuyTypeInvestment(Long userId, BuyStockRequest buyStockRequest, BigDecimal totalPrice) {
        Investment investment = mapToInvestment(userId, InvestType.BUY, buyStockRequest, totalPrice);
        return investmentOutPort.saveInvestment(investment);
    }
}
