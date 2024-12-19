package codeping.flex.investment.application.service.domain;

import codeping.flex.investment.adapter.out.webclient.data.StockImageDto;
import codeping.flex.investment.application.ports.in.investment.domain.StockImageUsecase;
import codeping.flex.investment.application.ports.out.StockImagePort;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@ApplicationService
@RequiredArgsConstructor
public class StockImageService implements StockImageUsecase {
    private final StockImagePort stockImagePort;

    @Override
    public Mono<List<StockImageDto>> getStockImageUrls(List<String> stockCodes) {
        return stockImagePort.getStockImages(stockCodes);
    }

}
