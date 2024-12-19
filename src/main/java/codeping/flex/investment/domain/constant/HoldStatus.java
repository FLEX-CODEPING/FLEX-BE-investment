package codeping.flex.investment.domain.constant;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum HoldStatus {
    HOLDING("보유 중"),        // 현재 종목을 보유 중
    SOLD("매도 완료");         // 매도 완료

    private final String description;
}
