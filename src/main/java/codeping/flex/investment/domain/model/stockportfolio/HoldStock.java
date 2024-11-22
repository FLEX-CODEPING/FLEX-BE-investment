package codeping.flex.investment.domain.model.stockportfolio;

import codeping.flex.investment.domain.constant.HoldStatus;
import codeping.flex.investment.domain.model.common.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HoldStock extends BaseTime {

    private Long holdStockId;
    private Long userId;
    private String corpName;
    private String stockCode;
    private long totalHoldings; // 총 보유량
    private HoldStatus holdStatus; // 보유 상태
    private Investment recentInvestment; // 최근 매도/매수 내역

    @Builder
    public HoldStock(Long userId, String corpName, String stockCode, long totalHoldings, HoldStatus holdStatus, Investment recentInvestment) {
        this.userId = userId;
        this.corpName = corpName;
        this.stockCode = stockCode;
        this.totalHoldings = totalHoldings;
        this.holdStatus = holdStatus;
        this.recentInvestment = recentInvestment;
    }

    public void addHoldings(long quantity) {
        this.totalHoldings += quantity;
        this.holdStatus = HoldStatus.HOLDING;
    }

    public void removeHoldings(long amount) {
        this.totalHoldings -= amount;
        if (this.totalHoldings == 0) {
            this.holdStatus = HoldStatus.SOLD;
        }
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
