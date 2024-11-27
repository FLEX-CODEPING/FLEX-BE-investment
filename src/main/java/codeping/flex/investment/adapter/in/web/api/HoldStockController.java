package codeping.flex.investment.adapter.in.web.api;

import codeping.flex.investment.adapter.in.web.data.holdstock.request.UserHoldStockRequest;
import codeping.flex.investment.adapter.in.web.data.holdstock.response.UserHoldStockResponse;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomSliceResponse;
import codeping.flex.investment.application.ports.in.investment.domain.HoldStockUseCase;
import codeping.flex.investment.global.annotation.architecture.WebAdapter;
import codeping.flex.investment.global.annotation.passport.Passport;
import codeping.flex.investment.global.annotation.passport.PassportInfo;
import codeping.flex.investment.global.common.response.ApplicationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/api/hold-stock")
@RequiredArgsConstructor
public class HoldStockController {

    private final HoldStockUseCase holdStockUseCase;

    @GetMapping
    @Operation(summary = "보유 종목 조회", description = "특정 유저의 보유 종목을 보유 상태에 따라 조회합니다.")
    public ApplicationResponse<CustomSliceResponse<UserHoldStockResponse>> getUserHoldStocks(
            @Parameter(hidden = true) @Passport PassportInfo passportInfo,
            @ModelAttribute @Valid UserHoldStockRequest userHoldStockRequest
    ) {
        CustomSliceResponse<UserHoldStockResponse> response = holdStockUseCase.getUserHoldStocks(passportInfo.userId(), userHoldStockRequest);
        return ApplicationResponse.onSuccess(response);
    }
}
