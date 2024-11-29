package codeping.flex.investment.adapter.in.web.api;

import codeping.flex.investment.adapter.in.web.data.investment.request.UserStockInvestmentRequest;
import codeping.flex.investment.adapter.in.web.data.investment.response.UserStockInvestmentResponse;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomSliceResponse;
import codeping.flex.investment.application.ports.in.investment.domain.InvestmentUseCase;
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
@RequestMapping("/api/investments")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentUseCase investmentUseCase;

    @GetMapping("/all")
    @Operation(summary = "특정 종목 매매 내역 조회", description = "특정 유저의 특정 종목에 대한 매매 내역을 조회합니다.")
    public ApplicationResponse<CustomSliceResponse<UserStockInvestmentResponse>> getAllUserStockInvestments(
            @Parameter(hidden = true) @Passport PassportInfo passportInfo,
            @ModelAttribute @Valid UserStockInvestmentRequest userStockInvestmentRequest
    ) {
        CustomSliceResponse<UserStockInvestmentResponse> response = investmentUseCase.getAllUserStockInvestments(
                passportInfo.userId(), userStockInvestmentRequest
        );
        return ApplicationResponse.onSuccess(response);
    }
}
