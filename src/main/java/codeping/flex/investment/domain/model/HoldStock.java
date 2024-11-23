package codeping.flex.investment.domain.model;

import codeping.flex.investment.domain.constant.HoldStatus;
import codeping.flex.investment.domain.model.common.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HoldStock extends BaseTime {

    private Long holdStockId;
    private Long userId;
    private String corpName;
    private String stockCode;
    private long totalHoldings; // 총 보유량
    private HoldStatus holdStatus; // 보유 상태
    private BigDecimal avgPrice; // 평단가
    private BigDecimal principal; // 원금
    private Investment recentInvestment; // 최근 매도/매수 내역

    @Builder
    public HoldStock(
            Long userId, String corpName, String stockCode,
            long totalHoldings, HoldStatus holdStatus, BigDecimal avgPrice, BigDecimal principal, Investment recentInvestment
    ) {
        this.userId = userId;
        this.corpName = corpName;
        this.stockCode = stockCode;
        this.totalHoldings = totalHoldings;
        this.holdStatus = holdStatus;
        this.avgPrice = avgPrice;
        this.principal = principal;
        this.recentInvestment = recentInvestment;
    }

    /**
     * 매수 시, 해당 보유 종목 정보를 업데이트합니다.
     *
     * @param quantity 매수 수량
     * @param totalPrice 매수 총 가격
     */
    public void buy(long quantity, BigDecimal totalPrice) {
        // 기존 투자금 + 신규 투자금으로 평단가 재계산
        BigDecimal currentTotalInvestment = this.avgPrice.multiply(BigDecimal.valueOf(this.totalHoldings));
        BigDecimal newTotalInvestment = currentTotalInvestment.add(totalPrice);

        // 보유 수량 업데이트
        this.totalHoldings += quantity;
        // 평단가 업데이트
        this.avgPrice = newTotalInvestment.divide(BigDecimal.valueOf(this.totalHoldings), BigDecimal.ROUND_HALF_UP);
        // 기존 원금에 매수 금액 추가
        this.principal = this.principal.add(totalPrice);
        // 상태 업데이트
        this.holdStatus = HoldStatus.HOLDING;
    }

    public boolean hasEnoughHoldings(long amount) {
        return this.totalHoldings >= amount;
    }

    /**
     * 해당 종목에 대한 최신 투자 데이터를 업데이트합니다.
     */
    public void setLatestInvestment(Investment recentInvestment) {
        this.recentInvestment = recentInvestment;
    }
}
