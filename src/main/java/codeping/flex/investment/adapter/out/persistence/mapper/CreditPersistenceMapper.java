package codeping.flex.investment.adapter.out.persistence.mapper;

import codeping.flex.investment.adapter.out.persistence.entity.CreditEntity;
import codeping.flex.investment.domain.model.Credit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditPersistenceMapper extends PersistenceMapper<CreditEntity, Credit> {

}
