package codeping.flex.investment.adapter.out.webclient.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class StockVolumeRankingResponse extends CommonResponse {
    private List<VolumeRanking> result;

    @Getter
    @NoArgsConstructor
    public static class VolumeRanking {
        private String corpName;
        private String stockCode;
        private String ranking;
        private String curPrice;
        private String priceChangeSign;
        private String priceChange;
        private String priceChangeRate;
        private String accTradingVolume;
        private String preDayTradingVolume;
        private String listedShares;
        private String avgTradingVolume;
        private String prevPeriodPriceChangeRate;
        private String volIncreaseRate;
        private String volTurnoverRate;
        private String periodVolTurnoverRate;
        private String avgTradingValue;
        private String accTradingValue;
    }
}