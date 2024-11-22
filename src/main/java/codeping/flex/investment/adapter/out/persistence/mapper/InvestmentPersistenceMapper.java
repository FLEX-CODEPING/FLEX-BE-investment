package codeping.flex.investment.adapter.out.persistence.mapper;

import codeping.flex.investment.adapter.out.persistence.entity.InvestmentEntity;
import codeping.flex.investment.domain.model.Investment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvestmentPersistenceMapper extends PersistenceMapper<InvestmentEntity, Investment> {

}
