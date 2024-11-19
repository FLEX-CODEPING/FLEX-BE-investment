package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.domain.model.stockportfolio.Investment;

import java.util.Optional;

public interface InvestmentOutPort {

    Investment saveInvestment(Investment investment);
    Optional<Investment> getInvestmentById(Long investmentId);
}
