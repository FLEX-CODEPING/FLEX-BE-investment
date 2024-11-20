package codeping.flex.investment.adapter.out.persistence.mapper;

public interface EntityMapper<ENTITY, DOMAIN> {

    ENTITY toEntity(final DOMAIN domain);

    DOMAIN toDomain(final ENTITY entity);
}