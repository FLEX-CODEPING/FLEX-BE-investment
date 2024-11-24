package codeping.flex.investment.adapter.in.web.api;

import codeping.flex.investment.adapter.in.web.data.trading.request.BuyStockRequest;
import codeping.flex.investment.adapter.in.web.data.trading.request.SellStockRequest;
import codeping.flex.investment.adapter.in.web.data.trading.response.BuyStockResponse;
import codeping.flex.investment.adapter.in.web.data.trading.response.SellStockResponse;
import codeping.flex.investment.application.ports.in.investment.TradingUseCase;
import codeping.flex.investment.global.annotation.architecture.WebAdapter;
import codeping.flex.investment.global.annotation.passport.Passport;
import codeping.flex.investment.global.annotation.passport.PassportInfo;
import codeping.flex.investment.global.annotation.swagger.ApiErrorCodes;
import codeping.flex.investment.global.common.response.ApplicationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static codeping.flex.investment.domain.exception.InvestmentErrorCode.BALANCE_NOT_SUFFICIENT;
import static codeping.flex.investment.domain.exception.RecentTransactionErrorCode.RECENT_TRANSACTION_NOT_FOUND;

@WebAdapter
@RestController
@RequestMapping("/api/trading")
@RequiredArgsConstructor
public class TradingController {

    private final TradingUseCase tradingUseCase;

    @PostMapping("/buy")
    @Operation(summary = "매수", description = "특정 종목에 대한 주식을 매수합니다.")
    @ApiErrorCodes(
            investmentErrors = {BALANCE_NOT_SUFFICIENT},
            recentTransactionErrors = {RECENT_TRANSACTION_NOT_FOUND}
    )
    public ApplicationResponse<BuyStockResponse> buyStocks(
            @Parameter(hidden = true) @Passport PassportInfo passportInfo,
            @RequestBody @Valid BuyStockRequest buyStockRequest
    ) {
        BuyStockResponse buyStockResponse = tradingUseCase.buyStocks(passportInfo.userId(), buyStockRequest);
        return ApplicationResponse.onSuccess(buyStockResponse);
    }

    @PostMapping("/sell")
    @Operation(summary = "매도", description = "특정 종목에 대한 주식을 매도합니다.")
    public ApplicationResponse<SellStockResponse> sellStocks(
            @Parameter(hidden = true) @Passport PassportInfo passportInfo,
            SellStockRequest sellStockRequest
    ) {
        SellStockResponse sellStockResponse = tradingUseCase.sellStocks(passportInfo.userId(), sellStockRequest);
        return ApplicationResponse.onSuccess(sellStockResponse);
    }
}
