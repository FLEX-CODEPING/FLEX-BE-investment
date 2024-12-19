package codeping.flex.investment.application.service.domain;

import codeping.flex.investment.application.ports.in.investment.domain.CreditUseCase;
import codeping.flex.investment.application.ports.in.investment.domain.TransactionUseCase;
import codeping.flex.investment.application.ports.out.CreditOutPort;
import codeping.flex.investment.domain.constant.CreditType;
import codeping.flex.investment.domain.model.Credit;
import codeping.flex.investment.global.annotation.architecture.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static codeping.flex.investment.application.mapper.CreditMapper.mapToCredit;

@ApplicationService
@RequiredArgsConstructor
public class CreditService implements CreditUseCase {

    private final CreditOutPort creditOutPort;

    private final TransactionUseCase transactionUseCase;

    @Override
    @Transactional
    public void saveCredit(Long userId, long credits, CreditType creditType, BigDecimal currentTotalProfit, BigDecimal currentBalance) {
        Credit credit = creditOutPort.saveCredit(mapToCredit(userId, credits, creditType));
        transactionUseCase.saveCreditTransaction(userId, credit, currentTotalProfit, currentBalance);
    }

    @Override
    @Transactional
    public void handleCreditSignUp(Long userId, long credits) {
        Optional<Credit> firstCredit = creditOutPort.getFirstCreditByUserId(userId);

        if (firstCredit.isEmpty()) {
            saveCredit(userId, credits, CreditType.SIGNUP, BigDecimal.ZERO, BigDecimal.valueOf(credits));
        }
    }
}
