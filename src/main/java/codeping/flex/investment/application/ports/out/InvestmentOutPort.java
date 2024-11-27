package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.domain.model.Investment;

import java.util.Optional;

public interface InvestmentOutPort {

    /** SAVE **/
    Investment saveInvestment(Investment investment);

    /** GET **/
    Optional<Investment> getInvestmentById(Long investmentId);
}
