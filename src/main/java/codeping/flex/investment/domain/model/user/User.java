package codeping.flex.investment.domain.model.user;

import codeping.flex.investment.adapter.out.persistence.entity.user.UserStatusType;
import codeping.flex.investment.domain.model.common.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTime {

    private Long userId;
    private Long socialId;
    private String email;
    private UserStatusType status;
    private UserInfo userInfo;

    @Builder
    public User(Long userId, Long socialId, String email, UserStatusType status, UserInfo userInfo) {
        this.userId = userId;
        this.socialId = socialId;
        this.email = email;
        this.status = status;
        this.userInfo = userInfo;
    }
}
