package codeping.flex.investment.domain.model.common;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseTime {

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
