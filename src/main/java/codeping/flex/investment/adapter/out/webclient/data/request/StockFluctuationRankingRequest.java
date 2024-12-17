package codeping.flex.investment.adapter.out.webclient.data.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockFluctuationRankingRequest {
    private String marketCode = "0000";
    private String fluctuationRateMin = "";
    private String fluctuationRateMax = "";
    private String sortOrder = "4";
    private String resultLimit = "0";
    private String priceType = "";
    private String priceMin = "";
    private String priceMax = "";
    private String volumeThreshold = "";
    private String targetType = "0";
    private String excludeType = "0";
    private String categoryType = "0";
}