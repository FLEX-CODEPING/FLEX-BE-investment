package codeping.flex.investment.adapter.out.persistence.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SalaryRange {

    LESS_3K("3000 이하"),
    LESS_5K("5000 이하"),
    LESS_8K("8000 이하"),
    LESS_100K("1억 이하"),
    LESS_150K("1억 5천 이하"),
    LESS_200K("2억 이하"),
    OVER_200K("2억 초과")
    ;

    private final String description;
}
