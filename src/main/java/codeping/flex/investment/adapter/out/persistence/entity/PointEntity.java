package codeping.flex.investment.adapter.out.persistence.entity;

import codeping.flex.investment.adapter.out.persistence.converter.PointTypeConverter;
import codeping.flex.investment.adapter.out.persistence.entity.common.BaseTimeEntity;
import codeping.flex.investment.domain.constant.PointType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "point")
public class PointEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long pointId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private long point;

    @Convert(converter = PointTypeConverter.class)
    @Column(nullable = false)
    private PointType type;

    @Builder
    public PointEntity(Long userId, long point, PointType pointType) {
        this.userId = userId;
        this.point = point;
        this.type = pointType;
    }
}
