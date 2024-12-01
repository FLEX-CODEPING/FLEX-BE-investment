package codeping.flex.investment.application.service.domain;

import codeping.flex.investment.adapter.in.web.data.investment.request.UserStockInvestmentRequest;
import codeping.flex.investment.adapter.in.web.data.investment.response.UserStockInvestmentResponse;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomSliceResponse;
import codeping.flex.investment.adapter.in.web.data.investment.request.BuyStockRequest;
import codeping.flex.investment.adapter.in.web.data.investment.request.SellStockRequest;
import codeping.flex.investment.application.ports.in.investment.domain.InvestmentUseCase;
import codeping.flex.investment.application.ports.out.InvestmentOutPort;
import codeping.flex.investment.domain.constant.InvestType;
import codeping.flex.investment.domain.model.Investment;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static codeping.flex.investment.application.mapper.InvestmentMapper.mapToInvestment;

@ApplicationService
@RequiredArgsConstructor
public class InvestmentService implements InvestmentUseCase {

    private final InvestmentOutPort investmentOutPort;

    @Override
    @Transactional
    public Investment saveBuyTypeInvestment(Long userId, BuyStockRequest buyStockRequest, BigDecimal totalPrice) {
        Investment investment = mapToInvestment(
                userId, buyStockRequest.stockCode(), buyStockRequest.corpName(), InvestType.BUY,
                buyStockRequest.quantity(), buyStockRequest.price(), totalPrice, BigDecimal.ZERO
        );
        return investmentOutPort.saveInvestment(investment);
    }

    @Override
    @Transactional
    public Investment saveSellTypeInvestment(Long userId, SellStockRequest sellStockRequest, BigDecimal totalPrice, BigDecimal profit) {
        Investment investment = mapToInvestment(
                userId, sellStockRequest.stockCode(), sellStockRequest.corpName(), InvestType.SELL,
                sellStockRequest.quantity(), sellStockRequest.price(), totalPrice, profit
        );
        return investmentOutPort.saveInvestment(investment);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomSliceResponse<UserStockInvestmentResponse> getAllUserStockInvestments(Long userId, UserStockInvestmentRequest request) {
        Slice<Investment> investmentSlice = investmentOutPort.getAllInvestmentsByUserIdAndStockCode(
                userId, request.stockCode(), request.customPageRequest().toPageRequest()
        );

        List<UserStockInvestmentResponse> content = investmentSlice.getContent()
                .stream()
                .map(UserStockInvestmentResponse::from)
                .toList();

        return CustomSliceResponse.of(content, investmentSlice);
    }
}
