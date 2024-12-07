package codeping.flex.investment.application.service.domain;

import codeping.flex.investment.adapter.in.web.data.holdstock.response.UserHoldStockResponse;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomPageRequest;
import codeping.flex.investment.adapter.in.web.data.pagination.CustomSliceResponse;
import codeping.flex.investment.application.ports.in.investment.domain.HoldStockUseCase;
import codeping.flex.investment.application.ports.out.HoldStockOutPort;
import codeping.flex.investment.domain.constant.HoldStatus;
import codeping.flex.investment.domain.model.HoldStock;
import codeping.flex.investment.domain.model.Investment;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import codeping.flex.investment.global.common.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static codeping.flex.investment.application.mapper.HoldStockMapper.mapToHoldStock;
import static codeping.flex.investment.domain.exception.HoldStockErrorCode.HOLD_STOCK_NOT_EXIST;
import static codeping.flex.investment.domain.exception.HoldStockErrorCode.HOLD_STOCK_NOT_FOUND;

@ApplicationService
@RequiredArgsConstructor
public class HoldStockService implements HoldStockUseCase {

    private final HoldStockOutPort holdStockOutPort;

    @Override
    @Transactional
    public HoldStock saveHoldStock(Long userId, String stockCode, String corpName, long quantity, Investment investment) {
        HoldStock holdStock = mapToHoldStock(userId, stockCode, corpName, quantity, HoldStatus.HOLDING, investment);
        return holdStockOutPort.saveHoldStock(holdStock);
    }

    @Override
    @Transactional
    public HoldStock saveHoldStock(HoldStock holdStock) {
        return holdStockOutPort.saveHoldStock(holdStock);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HoldStock> getHoldStockByUserIdAndStockCode(Long userId, String stockCode) {
        return holdStockOutPort.getHoldStockByUserIdAndStockCode(userId, stockCode);
    }

    /**
     * 특정 유저가 보유하고 있는 종목을 HoldStatus 에 따라 조회하여 반환합니다.
     */
    @Override
    @Transactional(readOnly = true)
    public HoldStock getHoldStockById(Long holdStockId) {
        return holdStockOutPort.getHoldStockById(holdStockId)
                .orElseThrow(() -> ApplicationException.from(HOLD_STOCK_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomSliceResponse<UserHoldStockResponse> getUserHoldStocks(Long userId, HoldStatus holdStatus, CustomPageRequest userHoldStockRequest) {
        Slice<HoldStock> holdStockSlice = holdStockOutPort.getHoldStocksByUserIdAndHoldStatus(
                userId, holdStatus, userHoldStockRequest.toPageRequest()
        );

        List<UserHoldStockResponse> content = holdStockSlice.getContent()
                .stream()
                .map(UserHoldStockResponse::from)
                .toList();

        return CustomSliceResponse.of(content, holdStockSlice);
    }

    @Override
    @Transactional(readOnly = true)
    public UserHoldStockResponse getUserHoldStockByStockCode(Long userId, String stockCode){
        HoldStock holdStock = holdStockOutPort.getHoldStockByUserIdAndStockCode(userId, stockCode)
                .orElseThrow(() -> ApplicationException.from(HOLD_STOCK_NOT_EXIST));

        return UserHoldStockResponse.from(holdStock);
    }

    /**
     * user 가 이미 보유한 종목이라면 보유 수량, 상태, 평단가를 업데이트합니다.
     * user 가 보유하고 있지 않은 종목이라면 신규 저장합니다.
     *
     * @param userId user PK
     * @param investment 매수 정보
     */
    @Override
    @Transactional
    public void updateBuyOrCreateHoldStock(Long userId, Investment investment) {
        Optional<HoldStock> holdStock = getHoldStockByUserIdAndStockCode(userId, investment.getStockCode());

        holdStock.ifPresentOrElse(
                stock -> {
                    stock.buy(investment.getQuantity(), investment.getTotalPrice(), investment);
                    holdStockOutPort.saveHoldStock(stock);
                },
                () -> saveHoldStock(userId, investment.getStockCode(), investment.getCorpName(), investment.getQuantity(), investment)
        );
    }
}
