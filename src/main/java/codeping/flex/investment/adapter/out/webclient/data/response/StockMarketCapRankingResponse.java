package codeping.flex.investment.adapter.out.webclient.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
public class StockMarketCapRankingResponse extends CommonResponse {
    private final List<MarketCapRanking> result = Collections.emptyList();

    @Getter
    @NoArgsConstructor
    public static class MarketCapRanking {
        private String stockCode;
        private String corpName;
        private String ranking;
        private String curPrice;
        private String priceChange;
        private String priceChangeSign;
        private String priceChangeRate;
        private String accTradingVol;
        private String listedShares;
        private String marketCap;
        private String marketRatio;
    }
}