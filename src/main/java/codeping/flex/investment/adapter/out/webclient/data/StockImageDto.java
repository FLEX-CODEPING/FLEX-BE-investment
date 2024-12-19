package codeping.flex.investment.adapter.out.webclient.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockImageDto {
    private String stockcode;
    private String symbolImageUrl;
}