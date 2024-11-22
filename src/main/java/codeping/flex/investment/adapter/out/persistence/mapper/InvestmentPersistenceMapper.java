package codeping.flex.investment.adapter.out.persistence.mapper;

import codeping.flex.investment.adapter.out.persistence.entity.stockportfolio.InvestmentEntity;
import codeping.flex.investment.domain.model.stockportfolio.Investment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InvestmentPersistenceMapper extends PersistenceMapper<InvestmentEntity, Investment> {

}
