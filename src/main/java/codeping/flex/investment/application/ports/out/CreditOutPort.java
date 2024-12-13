package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.domain.model.Credit;

import java.util.Optional;

public interface CreditOutPort {

    /** SAVE **/
    Credit saveCredit(Credit credit);

    /** GET **/
    Optional<Credit> getFirstCreditByUserId(Long userId);
}
