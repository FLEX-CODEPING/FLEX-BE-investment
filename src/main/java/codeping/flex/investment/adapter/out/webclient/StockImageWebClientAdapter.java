package codeping.flex.investment.adapter.out.webclient;

import codeping.flex.investment.adapter.out.webclient.data.StockImageDto;
import codeping.flex.investment.application.ports.out.StockImagePort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class StockImageWebClientAdapter implements StockImagePort {

    private final WebClient webClient;

    public StockImageWebClientAdapter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://your.api.base.url").build();
    }

    @Override
    public Mono<List<StockImageDto>> getStockImages(List<String> stockCodes) {
        return webClient.get()
                .uri("/api/stocks/image-url?stockcodes={stockCodes}", String.join(",", stockCodes))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<StockImageDto>>() {})
                .onErrorResume(e -> Mono.just(List.of()));
    }
}
