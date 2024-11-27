package codeping.flex.investment.application.ports.out;

import codeping.flex.investment.domain.constant.HoldStatus;
import codeping.flex.investment.domain.model.HoldStock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface HoldStockOutPort {

    /** SAVE **/
    HoldStock saveHoldStock(HoldStock holdStock);

    /** GET **/
    Optional<HoldStock> getHoldStockByUserIdAndStockCode(Long userId, String stockCode);
    Slice<HoldStock> getHoldStocksByUserIdAndHoldStatus(Long userId, HoldStatus holdStatus, Pageable pageable);
}
