package codeping.flex.investment.adapter.out.persistence.mapper;

import codeping.flex.investment.adapter.out.persistence.entity.transaction.InvestmentEntity;
import codeping.flex.investment.domain.model.Investment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InvestmentPersistenceMapper {

    InvestmentPersistenceMapper INSTANCE = Mappers.getMapper(InvestmentPersistenceMapper.class);

    InvestmentEntity toInvestmentEntity(Investment investment);
    Investment toInvestment(InvestmentEntity entity);
}
