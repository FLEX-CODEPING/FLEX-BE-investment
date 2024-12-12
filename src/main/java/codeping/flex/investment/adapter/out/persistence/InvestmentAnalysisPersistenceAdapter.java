package codeping.flex.investment.adapter.out.persistence;

import codeping.flex.investment.adapter.out.persistence.entity.InvestmentEntity;
import codeping.flex.investment.adapter.out.persistence.mapper.InvestmentPersistenceMapper;
import codeping.flex.investment.adapter.out.persistence.repository.InvestmentRepository;
import codeping.flex.investment.application.ports.out.InvestmentAnalysisOutPort;
import codeping.flex.investment.domain.model.Investment;
import codeping.flex.investment.global.annotation.architecture.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static codeping.flex.investment.application.openai.prompt.OpenAiPrompts.INVESTMENT_HISTORY_FORMAT;

@PersistenceAdapter
@RequiredArgsConstructor
public class InvestmentAnalysisPersistenceAdapter implements InvestmentAnalysisOutPort {

    private final InvestmentRepository investmentRepository;
    private final InvestmentPersistenceMapper investmentPersistenceMapper;

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

}
