package codeping.flex.investment.adapter.out.persistence.entity.user;

import codeping.flex.investment.adapter.out.persistence.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private Long socialId;

    @Column
    private String email;

    @Column(nullable = false)
    @Enumerated(value = STRING)
    private UserStatusType status;

    @Embedded
    private UserInfoEntity userInfo;

    @Builder
    public UserEntity(Long userId, Long socialId, String email, UserStatusType status, UserInfoEntity userInfo) {
        this.userId = userId;
        this.socialId = socialId;
        this.email = email;
        this.status = status;
        this.userInfo = userInfo;
    }
}
