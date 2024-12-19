package codeping.flex.investment.application.ports.in.investment.domain;

import codeping.flex.investment.adapter.out.webclient.data.StockImageDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StockImageUsecase {
    Mono<List<StockImageDto>> getStockImageUrls(List<String> stockCodes);
}
