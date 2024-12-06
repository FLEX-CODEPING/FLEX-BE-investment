package codeping.flex.investment.adapter.in.web.api;

import codeping.flex.investment.adapter.in.web.data.holdstock.response.UserHoldStockResponse;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomPageRequest;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomSliceResponse;
import codeping.flex.investment.application.ports.in.investment.domain.HoldStockUseCase;
import codeping.flex.investment.domain.constant.HoldStatus;
import codeping.flex.investment.global.annotation.architecture.WebAdapter;
import codeping.flex.investment.global.annotation.passport.Passport;
import codeping.flex.investment.global.annotation.passport.PassportInfo;
import codeping.flex.investment.global.common.response.ApplicationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("/api/hold-stocks")
@RequiredArgsConstructor
public class HoldStockController {

    private final HoldStockUseCase holdStockUseCase;

    @GetMapping
    @Operation(summary = "보유 종목 조회", description = "특정 유저의 보유 종목을 보유 상태에 따라 조회합니다.")
    public ApplicationResponse<CustomSliceResponse<UserHoldStockResponse>> getUserHoldStocks(
            @Parameter(hidden = true) @Passport PassportInfo passportInfo,
            @Parameter(description = "보유 상태 (HOLDING | SOLD)", example = "HOLDING") @RequestParam(value = "holdStatus") HoldStatus holdStatus,
            @ModelAttribute @Valid CustomPageRequest customPageRequest
    ) {
        CustomSliceResponse<UserHoldStockResponse> response = holdStockUseCase.getUserHoldStocks(passportInfo.userId(), holdStatus, customPageRequest);
        return ApplicationResponse.onSuccess(response);
    }

    @GetMapping
    @Operation(summary = "보유 종목 개별 정보 조회", description = "특정 유저의 개별 보유 종목을 정보를 조회합니다.")
    public ApplicationResponse<UserHoldStockResponse> getUserHoldStockByStockCode(
            @Parameter(hidden = true) @Passport PassportInfo passportInfo,
            @Parameter(description = "종목 코드", example = "005930") @RequestParam(value = "stockCode") String stockCode
    ) {
        UserHoldStockResponse response = holdStockUseCase.getUserHoldStockByStockCode(passportInfo.userId(), stockCode);
        return ApplicationResponse.onSuccess(response);
    }
}
