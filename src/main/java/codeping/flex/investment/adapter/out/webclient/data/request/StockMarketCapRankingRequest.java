package codeping.flex.investment.adapter.out.webclient.data.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockMarketCapRankingRequest {
    private String divClassCode = "0";
    private String stockCode = "0000";
    private String volCount = "";
}