package codeping.flex.investment.domain.model;

import codeping.flex.investment.domain.constant.InvestType;
import codeping.flex.investment.domain.model.common.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Investment extends BaseTime {

    private Long investmentId;
    private Long userId;
    private String corpName;
    private String stockCode;
    private InvestType investType;
    private int quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private BigDecimal profit; // 매매 차익

    @Builder
    public Investment(Long userId, String stockCode, String corpName, InvestType investType, int quantity, BigDecimal price, BigDecimal totalPrice, BigDecimal profit) {
        this.userId = userId;
        this.stockCode = stockCode;
        this.corpName = corpName;
        this.investType = investType;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.profit = profit;
    }

    /**
     * 해당 주식에 대한 최근 거래 내역을 참조하여,
     * 주어진 수량만큼 주식을 판매하고, 판매 가격에 따라 총 판매 금액 및 이익을 계산합니다.
     *
     * @param sellAmount 판매할 주식의 수량
     * @param sellPrice  주식의 판매 가격
     */
    public void sellStock(int sellAmount, BigDecimal sellPrice) {
        // 총 판매 금액을 계산
        BigDecimal sellTotalPrice = sellPrice.multiply(new BigDecimal(sellAmount));
        BigDecimal profit = (sellPrice.subtract(this.price)).multiply(new BigDecimal(sellAmount));

        // 보유 주식 수량에서 판매한 수량을 차감
        this.quantity -= sellAmount;
        this.profit = profit;
        this.totalPrice = sellTotalPrice;

        this.investType = InvestType.SELL;
    }
}
