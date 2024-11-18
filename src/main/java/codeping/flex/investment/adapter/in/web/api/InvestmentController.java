package codeping.flex.investment.adapter.in.web.api;

import codeping.flex.investment.application.ports.in.investment.TradingUseCase;
import codeping.flex.investment.global.annotation.architecture.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/api/investments")
@RequiredArgsConstructor
public class InvestmentController {

    private final TradingUseCase tradingUseCase;

}
