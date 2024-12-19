package codeping.flex.investment.adapter.out.persistence.mapper;

import codeping.flex.investment.adapter.out.persistence.entity.common.BaseTimeEntity;
import codeping.flex.investment.domain.model.common.BaseTime;
import org.mapstruct.InheritInverseConfiguration;

public interface PersistenceMapper<ENTITY extends BaseTimeEntity, DOMAIN extends BaseTime> {

    ENTITY toEntity(final DOMAIN domain);

    @InheritInverseConfiguration
    DOMAIN toDomain(final ENTITY entity);
}