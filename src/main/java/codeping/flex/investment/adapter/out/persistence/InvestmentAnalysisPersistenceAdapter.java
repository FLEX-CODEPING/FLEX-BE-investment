package codeping.flex.investment.adapter.out.persistence;

import codeping.flex.investment.adapter.out.persistence.entity.InvestmentEntity;
import codeping.flex.investment.adapter.out.persistence.mapper.InvestmentPersistenceMapper;
import codeping.flex.investment.adapter.out.persistence.repository.InvestmentRepository;
import codeping.flex.investment.adapter.out.webclient.data.request.StockFluctuationRankingRequest;
import codeping.flex.investment.adapter.out.webclient.data.request.StockMarketCapRankingRequest;
import codeping.flex.investment.adapter.out.webclient.data.request.StockVolumeRankingRequest;
import codeping.flex.investment.application.ports.out.InvestmentAnalysisOutPort;
import codeping.flex.investment.application.ports.out.StockRankingPort;
import codeping.flex.investment.domain.model.Investment;
import codeping.flex.investment.global.annotation.architecture.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static codeping.flex.investment.application.openai.prompt.OpenAiPrompts.*;

@PersistenceAdapter
@RequiredArgsConstructor
public class InvestmentAnalysisPersistenceAdapter implements InvestmentAnalysisOutPort {

    private final InvestmentRepository investmentRepository;
    private final InvestmentPersistenceMapper investmentPersistenceMapper;
    private final StockRankingPort stockRankingPort;

    @Override
    public String getAllInvestmentsByUserId(Long userId) {
        final List<InvestmentEntity> investmentEntities = this.investmentRepository.findAllByUserId(userId);
        if (investmentEntities == null || investmentEntities.isEmpty()) {
            return "[]";
        }

        return investmentEntities.stream()
                .map(investment -> {
                    Investment domain = this.investmentPersistenceMapper.toDomain(investment);
                    return String.format(INVESTMENT_HISTORY_FORMAT,
                            domain.getStockCode(),
                            domain.getCorpName(),
                            domain.getInvestType().name(),
                            domain.getQuantity(),
                            domain.getPrice().toString(),
                            domain.getTotalPrice().toString(),
                            domain.getProfit().toString(),
                            domain.getCreatedAt().toString());
                })
                .collect(Collectors.joining(",", "[", "]"));
    }

    @Override
    public long countInvestmentsByUserId(Long userId) {
        return this.investmentRepository.countByUserId(userId);
    }

    @Override
    public String[] getTrend() {
        StockFluctuationRankingRequest stockFluctuationRankingRequest = new StockFluctuationRankingRequest();
        StockVolumeRankingRequest stockVolumeRankingRequest = new StockVolumeRankingRequest();
        StockMarketCapRankingRequest stockMarketCapRankingRequest = new StockMarketCapRankingRequest();

        Mono<String> fluctuationResultMono = stockRankingPort.getFluctuationRanking(stockFluctuationRankingRequest)
            .map(response -> Optional.ofNullable(response.getResult())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream()
                    .limit(10)
                    .map(ranking -> String.format(FLUCTUATION_RANKING_FORMAT,
                        ranking.getStockCode(),
                        ranking.getDataRank(),
                        ranking.getStockName(),
                        ranking.getCurPrice(),
                        ranking.getPriceChange(),
                        ranking.getPriceChangeSign(),
                        ranking.getPriceChangeRate(),
                        ranking.getAccVolume(),
                        ranking.getHighPrice(),
                        ranking.getHighPriceTime(),
                        ranking.getHighPriceDate(),
                        ranking.getLowPrice(),
                        ranking.getLowPriceTime(),
                        ranking.getLowPriceDate(),
                        ranking.getLowPriceToCurRate(),
                        ranking.getClosingPriceToCurRate(),
                        ranking.getContinuousRiseDays(),
                        ranking.getHighPriceToCurRate(),
                        ranking.getContinuousFallDays(),
                        ranking.getOpenPriceChangeSign(),
                        ranking.getOpenPriceChange(),
                        ranking.getOpenPriceChangeRate(),
                        ranking.getPeriodChange(),
                        ranking.getPeriodChangeRate()))
                    .collect(Collectors.joining(",", "[", "]")))
                .orElse("[]"));

        Mono<String> volumeResultMono = stockRankingPort.getVolumeRanking(stockVolumeRankingRequest)
            .map(response -> Optional.ofNullable(response.getResult())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream()
                    .limit(10)
                    .map(ranking -> String.format(VOLUME_RANKING_FORMAT,
                        ranking.getCorpName(),
                        ranking.getStockCode(),
                        ranking.getRanking(),
                        ranking.getCurPrice(),
                        ranking.getPriceChangeSign(),
                        ranking.getPriceChange(),
                        ranking.getPriceChangeRate(),
                        ranking.getAccTradingVolume(),
                        ranking.getPreDayTradingVolume(),
                        ranking.getListedShares(),
                        ranking.getAvgTradingVolume(),
                        ranking.getPrevPeriodPriceChangeRate(),
                        ranking.getVolIncreaseRate(),
                        ranking.getVolTurnoverRate(),
                        ranking.getPeriodVolTurnoverRate(),
                        ranking.getAvgTradingValue(),
                        ranking.getAccTradingValue()))
                    .collect(Collectors.joining(",", "[", "]")))
                .orElse("[]"));

        Mono<String> marketCapResultMono = stockRankingPort.getMarketCapRanking(stockMarketCapRankingRequest)
            .map(response -> Optional.ofNullable(response.getResult())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream()
                    .limit(10)
                    .map(ranking -> String.format(MARKET_CAP_RANKING_FORMAT,
                        ranking.getStockCode(),
                        ranking.getRanking(),
                        ranking.getCorpName(),
                        ranking.getCurPrice(),
                        ranking.getPriceChange(),
                        ranking.getPriceChangeSign(),
                        ranking.getPriceChangeRate(),
                        ranking.getAccTradingVol(),
                        ranking.getListedShares(),
                        ranking.getMarketCap(),
                        ranking.getMarketRatio()))
                    .collect(Collectors.joining(",", "[", "]")))
                .orElse("[]"));

        return Mono.zip(fluctuationResultMono, volumeResultMono, marketCapResultMono)
            .map(tuple -> new String[] { tuple.getT1(), tuple.getT2(), tuple.getT3() })
            .onErrorResume(e -> Mono.error(new RuntimeException("Failed to get rankings", e))).block();
    }

}
