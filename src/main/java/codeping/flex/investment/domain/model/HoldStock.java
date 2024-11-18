package codeping.flex.investment.domain.model;

import codeping.flex.investment.domain.constant.HoldStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HoldStock {

    private Long id;
    private Long userId;
    private String corpName;
    private String stockCode;
    private long totalHoldings; // 총 보유량
    private HoldStatus holdStatus; // 보유 상태
    private Long investmentId; // 최근 매도/매수 내역

    @Builder
    public HoldStock(Long userId, String corpName, String stockCode, long totalHoldings, Long investmentId) {
        this.userId = userId;
        this.corpName = corpName;
        this.stockCode = stockCode;
        this.totalHoldings = totalHoldings;
        this.holdStatus = HoldStatus.HOLDING;
        this.investmentId = investmentId;
    }

    public void addHoldings(long amount) {
        this.totalHoldings += amount;
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
     * @param investmentId
     */
    public void setLatestInvestment(Long investmentId) {
        this.investmentId = investmentId;
    }
}
