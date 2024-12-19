package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.domain.model.Investment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface InvestmentOutPort {

    /** SAVE **/
    Investment saveInvestment(Investment investment);

    /** GET **/
    Optional<Investment> getInvestmentById(Long investmentId);
    Slice<Investment> getAllInvestmentsByUserIdAndStockCode(final Long userId, final String stockCode, Pageable pageable);
}
