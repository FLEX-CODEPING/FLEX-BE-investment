package codeping.flex.investment.adapter.in.web.api;

import codeping.flex.investment.adapter.in.web.data.investment.response.RankingResponse;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomPageRequest;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomSliceResponse;
import codeping.flex.investment.adapter.in.web.data.transaction.response.UserTransactionResponse;
import codeping.flex.investment.adapter.in.web.data.transaction.response.UserTransactionSummaryResponse;
import codeping.flex.investment.application.ports.in.investment.domain.RecentTransactionUseCase;
import codeping.flex.investment.application.ports.in.investment.domain.TransactionUseCase;
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

import java.util.List;

@WebAdapter
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionUseCase transactionUseCase;
    private final RecentTransactionUseCase recentTransactionUseCase;

    @GetMapping
    @Operation(summary = "유저 거래 내역 전체 조회", description = "특정 유저의 전체 거래 내역을 조회합니다.")
    public ApplicationResponse<CustomSliceResponse<UserTransactionResponse>> getAllUserTransactions(
            @Parameter(hidden = true) @Passport PassportInfo passportInfo,
            @ModelAttribute @Valid CustomPageRequest customPageRequest
    ) {
        CustomSliceResponse<UserTransactionResponse> response = transactionUseCase.getAllUserTransactions(passportInfo.userId(), customPageRequest);
        return ApplicationResponse.onSuccess(response);
    }

    @GetMapping("/summary")
    @Operation(summary = "유저 보유 크레딧 및 수익 조회", description = "특정 유저가 보유한 총 크레딧 액수와 총 수익을 조회합니다.")
    public ApplicationResponse<UserTransactionSummaryResponse> getUserTransactionSummary(
            @Parameter(hidden = true) @Passport PassportInfo passportInfo
    ) {
        UserTransactionSummaryResponse response = recentTransactionUseCase.getUserTransactionSummary(passportInfo.userId());
        return ApplicationResponse.onSuccess(response);
    }

    @GetMapping("/rankings")
    @Operation(summary = "모의투자 랭킹 조회", description = "매매 총 수익을 기준으로 상위 3명의 유저를 선별하여 랭킹 정보를 제공합니다.")
    public ApplicationResponse<List<RankingResponse>> getRankings() {
        List<RankingResponse> rankings = transactionUseCase.getRankings();
        return ApplicationResponse.onSuccess(rankings);
    }
}
