package codeping.flex.investment.adapter.out.webclient;

import codeping.flex.investment.adapter.out.webclient.data.response.StockMarketCapRankingResponse;
import codeping.flex.investment.adapter.out.webclient.data.response.StockFluctuationRankingResponse;
import codeping.flex.investment.adapter.out.webclient.data.response.StockVolumeRankingResponse;
import codeping.flex.investment.adapter.out.webclient.data.request.StockFluctuationRankingRequest;
import codeping.flex.investment.adapter.out.webclient.data.request.StockMarketCapRankingRequest;
import codeping.flex.investment.adapter.out.webclient.data.request.StockVolumeRankingRequest;
import codeping.flex.investment.application.ports.out.StockRankingPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class StockRankingWebClientAdapter implements StockRankingPort {

    private final WebClient stockWebClient;

    private static final String FLUCTUATION_PATH = "/api/kis/stocks/ranking/fluctuation";
    private static final String VOLUME_PATH = "/api/kis/stocks/ranking/volume";
    private static final String MARKET_CAP_PATH = "/api/kis/stocks/ranking/market-cap";

    public StockRankingWebClientAdapter(WebClient.Builder webClientBuilder, @Value("${webclient.base-url}") String baseUrl) {
        this.stockWebClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @Override
    public Mono<StockFluctuationRankingResponse> getFluctuationRanking(StockFluctuationRankingRequest stockFluctuationRankingRequest) {
        return stockWebClient
                .post()
                .uri(FLUCTUATION_PATH)
                .bodyValue(stockFluctuationRankingRequest)
                .retrieve()
                .bodyToMono(StockFluctuationRankingResponse.class)
                .doOnError(e -> {
                    System.out.println("등락율 순위 조회 실패 - Request: " + stockFluctuationRankingRequest + ", Error: " + e.getMessage());
                })
                .onErrorResume(e -> Mono.just(new StockFluctuationRankingResponse()));
    }

    @Override
    public Mono<StockVolumeRankingResponse> getVolumeRanking(StockVolumeRankingRequest stockVolumeRankingRequest) {
        return stockWebClient
                .post()
                .uri(VOLUME_PATH)
                .bodyValue(stockVolumeRankingRequest)
                .retrieve()
                .bodyToMono(StockVolumeRankingResponse.class)
                .doOnError(e -> {
                    System.out.println("거래량 순위 조회 실패 - Request: " + stockVolumeRankingRequest + ", Error: " + e.getMessage());
                })
                .onErrorResume(e -> Mono.just(new StockVolumeRankingResponse()));
    }

    @Override
    public Mono<StockMarketCapRankingResponse> getMarketCapRanking(StockMarketCapRankingRequest stockMarketCapRankingRequest) {
        return stockWebClient
                .post()
                .uri(MARKET_CAP_PATH)
                .bodyValue(stockMarketCapRankingRequest)
                .retrieve()
                .bodyToMono(StockMarketCapRankingResponse.class)
                .doOnError(e -> {
                    System.out.println("시가총액 순위 조회 실패 - Request: " + stockMarketCapRankingRequest + ", Error: " + e.getMessage());
                })
                .onErrorResume(e -> Mono.just(new StockMarketCapRankingResponse()));
    }
}