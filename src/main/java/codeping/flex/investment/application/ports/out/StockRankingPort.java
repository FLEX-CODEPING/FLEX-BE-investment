package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.adapter.out.webclient.data.response.StockFluctuationRankingResponse;
import codeping.flex.investment.adapter.out.webclient.data.response.StockMarketCapRankingResponse;
import codeping.flex.investment.adapter.out.webclient.data.response.StockVolumeRankingResponse;
import codeping.flex.investment.adapter.out.webclient.data.request.StockFluctuationRankingRequest;
import codeping.flex.investment.adapter.out.webclient.data.request.StockMarketCapRankingRequest;
import codeping.flex.investment.adapter.out.webclient.data.request.StockVolumeRankingRequest;
import reactor.core.publisher.Mono;

public interface StockRankingPort {
    Mono<StockFluctuationRankingResponse> getFluctuationRanking(StockFluctuationRankingRequest stockFluctuationRankingRequest);
    Mono<StockVolumeRankingResponse> getVolumeRanking(StockVolumeRankingRequest stockVolumeRankingRequest);
    Mono<StockMarketCapRankingResponse> getMarketCapRanking(StockMarketCapRankingRequest stockMarketCapRankingRequest);
}
