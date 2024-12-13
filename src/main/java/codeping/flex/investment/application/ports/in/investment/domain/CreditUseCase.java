package codeping.flex.investment.application.ports.in.investment.domain;

import codeping.flex.investment.domain.constant.CreditType;

import java.math.BigDecimal;

public interface CreditUseCase {

    /** SAVE **/
    void saveCredit(Long userId, long credits, CreditType creditType, BigDecimal currentTotalProfit, BigDecimal currentBalance);
    void handleCreditSignUp(Long userId, long credits);
}
