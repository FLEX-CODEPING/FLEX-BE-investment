package codeping.flex.investment.domain.model;

import codeping.flex.investment.domain.constant.HoldStatus;
import codeping.flex.investment.domain.model.common.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;

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
    private Investment recentInvestment; // 최근 투자 거래 내역

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
    public void buy(long quantity, BigDecimal totalPrice, Investment investment) {
        // 기존 투자금 + 신규 투자금으로 평단가 재계산
        BigDecimal currentTotalInvestment = this.avgPrice.multiply(BigDecimal.valueOf(this.totalHoldings));
        BigDecimal newTotalInvestment = currentTotalInvestment.add(totalPrice);

        // 보유 수량 업데이트
        this.totalHoldings += quantity;
        // 평단가 업데이트
        this.avgPrice = newTotalInvestment.divide(BigDecimal.valueOf(this.totalHoldings), MathContext.DECIMAL64);
        // 원금 업데이트
        this.principal = this.principal.add(totalPrice);
        // 상태 업데이트
        this.holdStatus = HoldStatus.HOLDING;
        // 최근 거래 업데이트
        this.recentInvestment = investment;
    }

    /**
     * 매도 시, 해당 보유 종목 정보를 업데이트합니다.
     *
     * @param quantity 매도 수량
     */
    public void sell(long quantity, Investment investment) {
        // 원금 재계산
        BigDecimal costBasis = this.avgPrice.multiply(BigDecimal.valueOf(quantity));
        this.principal = this.principal.subtract(costBasis);

        // 보유 수량 감소
        this.totalHoldings -= quantity;

        // 보유 수량이 0이면 SOLD 상태로 변경
        if (this.totalHoldings == 0) {
            this.holdStatus = HoldStatus.SOLD;
            this.avgPrice = BigDecimal.ZERO;
            this.principal = BigDecimal.ZERO;
        }

        // 최근 거래 업데이트
        this.recentInvestment = investment;
    }

    /**
     * 매도 수익을 계산합니다.
     *
     * @param quantity 매도 수량
     * @param sellPrice 매도 단가
     * @return 매도 수익
     */
    public BigDecimal calculateSellProfit(long quantity, BigDecimal sellPrice) {
        BigDecimal sellTotalPrice = sellPrice.multiply(BigDecimal.valueOf(quantity));
        BigDecimal costBasis = this.avgPrice.multiply(BigDecimal.valueOf(quantity));
        return sellTotalPrice.subtract(costBasis);
    }

    /**
     * 현재 보유 수량과 입력 수량을 비교합니다.
     */
    public boolean isHoldingsSufficient(long quantity) {
        return this.totalHoldings >= quantity;
    }
}
