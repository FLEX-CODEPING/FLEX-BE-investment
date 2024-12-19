package codeping.flex.investment.adapter.in.web.api;

import codeping.flex.investment.adapter.in.web.data.investment.request.BuyStockRequest;
import codeping.flex.investment.adapter.in.web.data.investment.request.SellStockRequest;
import codeping.flex.investment.adapter.in.web.data.investment.response.BuyStockResponse;
import codeping.flex.investment.adapter.in.web.data.investment.response.SellStockResponse;
import codeping.flex.investment.adapter.in.web.data.investment.response.UserStockInvestmentResponse;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomPageRequest;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomSliceResponse;
import codeping.flex.investment.application.ports.in.investment.TradingUseCase;
import codeping.flex.investment.application.ports.in.investment.domain.InvestmentUseCase;
import codeping.flex.investment.global.annotation.architecture.WebAdapter;
import codeping.flex.investment.global.annotation.passport.Passport;
import codeping.flex.investment.global.annotation.passport.PassportInfo;
import codeping.flex.investment.global.annotation.swagger.ApiErrorCodes;
import codeping.flex.investment.global.common.response.ApplicationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static codeping.flex.investment.domain.exception.HoldStockErrorCode.HOLD_STOCK_NOT_EXIST;
import static codeping.flex.investment.domain.exception.HoldStockErrorCode.HOLD_STOCK_NOT_SUFFICIENT;
import static codeping.flex.investment.domain.exception.InvestmentErrorCode.BALANCE_NOT_SUFFICIENT;
import static codeping.flex.investment.domain.exception.RecentTransactionErrorCode.RECENT_TRANSACTION_NOT_FOUND;

@WebAdapter
@RestController
@RequestMapping("/api/investments")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentUseCase investmentUseCase;
    private final TradingUseCase tradingUseCase;

    @PostMapping("/trading/buy")
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

    @PostMapping("/trading/sell")
    @Operation(summary = "매도", description = "특정 종목에 대한 주식을 매도합니다.")
    @ApiErrorCodes(
            recentTransactionErrors = {RECENT_TRANSACTION_NOT_FOUND},
            holdStockErrors = {HOLD_STOCK_NOT_EXIST, HOLD_STOCK_NOT_SUFFICIENT}
    )
    public ApplicationResponse<SellStockResponse> sellStocks(
            @Parameter(hidden = true) @Passport PassportInfo passportInfo,
            @RequestBody @Valid SellStockRequest sellStockRequest
    ) {
        SellStockResponse sellStockResponse = tradingUseCase.sellStocks(passportInfo.userId(), sellStockRequest);
        return ApplicationResponse.onSuccess(sellStockResponse);
    }

    @GetMapping
    @Operation(summary = "특정 종목 매매 내역 조회", description = "특정 유저의 특정 종목에 대한 매매 내역을 조회합니다.")
    public ApplicationResponse<CustomSliceResponse<UserStockInvestmentResponse>> getUserInvestmentsByStockCode(
            @Parameter(hidden = true) @Passport PassportInfo passportInfo,
            @Parameter(description = "종목 코드", example = "005930") @RequestParam(value = "stockCode") String stockCode,
            @ModelAttribute @Valid CustomPageRequest customPageRequest
    ) {
        CustomSliceResponse<UserStockInvestmentResponse> response = investmentUseCase.getUserInvestmentsByStockCode(
                passportInfo.userId(), stockCode, customPageRequest
        );
        return ApplicationResponse.onSuccess(response);
    }
}
