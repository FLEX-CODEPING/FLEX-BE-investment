package codeping.flex.investment.domain.model.point;

import codeping.flex.investment.domain.constant.PointType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point {

    private Long pointId;
    private Long userId;
    private long pointAmount;
    private PointType pointType;

    @Builder
    public Point(Long userId, long pointAmount, PointType pointType) {
        this.userId = userId;
        this.pointAmount = pointAmount;
        this.pointType = pointType;
    }

    public void addPoint(long amount) {
        this.pointAmount += amount;
    }
}