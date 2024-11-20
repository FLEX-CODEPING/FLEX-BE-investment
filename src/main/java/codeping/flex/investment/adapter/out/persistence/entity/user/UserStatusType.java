package codeping.flex.investment.adapter.out.persistence.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatusType {

    ACTIVE("정상"),
    PENDING("대기"),
    WITHDRAW("탈퇴"),
    ;

    private final String description;
}