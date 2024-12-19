package codeping.flex.investment.adapter.out.webclient.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommonResponse {
    private boolean isSuccess;
    private String code;
    private String message;
}