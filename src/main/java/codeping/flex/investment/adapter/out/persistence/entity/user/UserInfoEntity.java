package codeping.flex.investment.adapter.out.persistence.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class UserInfoEntity {

    @Column
    private LocalDate birth;

    @Column
    private boolean isBirthVisible;

    @Column
    private String nickname;

    @Column
    private String blogName;

    @Column
    private String notificationEmail;

    @Column
    @Enumerated(value = STRING)
    private SalaryRange salary;

    @Column
    private boolean isSalaryVisible;

    @Column
    private String profileImageUrl;

    @Builder
    public UserInfoEntity(
            LocalDate birth, boolean isBirthVisible, String nickname, String blogName, String notificationEmail,
            SalaryRange salary, boolean isSalaryVisible, String profileImageUrl
    ) {
        this.birth = birth;
        this.isBirthVisible = isBirthVisible;
        this.nickname = nickname;
        this.blogName = blogName;
        this.notificationEmail = notificationEmail;
        this.salary = salary;
        this.isSalaryVisible = isSalaryVisible;
        this.profileImageUrl = profileImageUrl;
    }
}
