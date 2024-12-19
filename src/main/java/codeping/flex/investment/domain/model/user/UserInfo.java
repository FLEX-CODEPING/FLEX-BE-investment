package codeping.flex.investment.domain.model.user;

import codeping.flex.investment.adapter.out.persistence.entity.user.SalaryRange;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class UserInfo {

    private LocalDate birth;
    private boolean isBirthVisible;
    private String nickname;
    private String blogName;
    private String notificationEmail;
    private SalaryRange salary;
    private boolean isSalaryVisible;
    private String profileImageUrl;

    @Builder
    public UserInfo(
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
