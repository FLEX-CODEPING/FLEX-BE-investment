package codeping.flex.investment.adapter.out.persistence.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;

@Getter
@NoArgsConstructor
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
}
