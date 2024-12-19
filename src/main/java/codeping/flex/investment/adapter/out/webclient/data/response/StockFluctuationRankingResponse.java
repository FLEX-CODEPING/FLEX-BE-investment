package codeping.flex.investment.adapter.out.webclient.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
public class StockFluctuationRankingResponse extends CommonResponse {
    private final List<FluctuationRanking> result = Collections.emptyList();

    @Getter
    @NoArgsConstructor
    public static class FluctuationRanking {
        private String stockCode;
        private String dataRank;
        private String stockName;
        private String curPrice;
        private String priceChange;
        private String priceChangeSign;
        private String priceChangeRate;
        private String accVolume;
        private String highPrice;
        private String highPriceTime;
        private String highPriceDate;
        private String lowPrice;
        private String lowPriceTime;
        private String lowPriceDate;
        private String lowPriceToCurRate;
        private String closingPriceToCurRate;
        private String continuousRiseDays;
        private String highPriceToCurRate;
        private String continuousFallDays;
        private String openPriceChangeSign;
        private String openPriceChange;
        private String openPriceChangeRate;
        private String periodChange;
        private String periodChangeRate;
    }
}