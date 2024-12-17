package codeping.flex.investment.adapter.out.webclient;

import codeping.flex.investment.adapter.out.webclient.data.response.StockMarketCapRankingResponse;
import codeping.flex.investment.adapter.out.webclient.data.response.StockFluctuationRankingResponse;
import codeping.flex.investment.adapter.out.webclient.data.response.StockVolumeRankingResponse;
import codeping.flex.investment.adapter.out.webclient.data.request.StockFluctuationRankingRequest;
import codeping.flex.investment.adapter.out.webclient.data.request.StockMarketCapRankingRequest;
import codeping.flex.investment.adapter.out.webclient.data.request.StockVolumeRankingRequest;
import codeping.flex.investment.application.ports.out.StockRankingPort;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class StockRankingWebClientAdapter implements StockRankingPort {

    private final WebClient stockWebClient;

    private static final String FLUCTUATION_PATH = "/api/kis/stocks/ranking/fluctuation";
    private static final String VOLUME_PATH = "/api/kis/stocks/ranking/volume";
    private static final String MARKET_CAP_PATH = "/api/kis/stocks/ranking/market-cap";

    public StockRankingWebClientAdapter(WebClient.Builder webClientBuilder) {
        this.stockWebClient = webClientBuilder.baseUrl("http://your.api.base.url").build();
    }

    @Override
    public Mono<StockFluctuationRankingResponse> getfluctuationRanking(StockFluctuationRankingRequest stockFluctuationRankingRequest) {
        return stockWebClient
                .post()
                .uri(FLUCTUATION_PATH)
                .body(Mono.just(stockFluctuationRankingRequest), StockFluctuationRankingRequest.class)
                .retrieve()
                .bodyToMono(StockFluctuationRankingResponse.class);
    }

    @Override
    public Mono<StockVolumeRankingResponse> getVolumeRanking(StockVolumeRankingRequest stockVolumeRankingRequest) {
        return stockWebClient
                .post()
                .uri(VOLUME_PATH)
                .body(Mono.just(stockVolumeRankingRequest), StockVolumeRankingRequest.class)
                .retrieve()
                .bodyToMono(StockVolumeRankingResponse.class);
    }

    @Override
    public Mono<StockMarketCapRankingResponse> getMarketCapRanking(StockMarketCapRankingRequest stockMarketCapRankingRequest) {
        return stockWebClient
                .post()
                .uri(MARKET_CAP_PATH)
                .body(Mono.just(stockMarketCapRankingRequest), StockMarketCapRankingRequest.class)
                .retrieve()
                .bodyToMono(StockMarketCapRankingResponse.class);
    }
}