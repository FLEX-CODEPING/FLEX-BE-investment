package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.adapter.out.webclient.data.StockImageDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StockImagePort {
    Mono<List<StockImageDto>> getStockImages(List<String> stockCodes);
}
