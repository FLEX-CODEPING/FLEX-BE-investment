package codeping.flex.investment.adapter.out.persistence.mapper;

import codeping.flex.investment.adapter.out.persistence.entity.stockportfolio.HoldStockEntity;
import codeping.flex.investment.domain.model.HoldStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = InvestmentPersistenceMapper.class)
public interface HoldStockPersistenceMapper {

    HoldStockPersistenceMapper INSTANCE = Mappers.getMapper(HoldStockPersistenceMapper.class);

    @Mapping(target = "recentInvestmentEntity", source = "recentInvestment")
    HoldStockEntity toEntity(HoldStock holdStock);

    @Mapping(target = "recentInvestment", source = "recentInvestment")
    HoldStock toDomain(HoldStockEntity entity);
}