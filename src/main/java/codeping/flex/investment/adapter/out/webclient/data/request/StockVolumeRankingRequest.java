package codeping.flex.investment.adapter.out.webclient.data.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockVolumeRankingRequest {
    private String belongCode = "0";
    private String classCode = "0";
    private String priceMax = "";
    private String priceMin = "";
    private String stockCode = "0000";
    private String volCount = "";
}