package codeping.flex.investment.adapter.out.persistence.mapper;

import codeping.flex.investment.adapter.out.persistence.entity.HoldStockEntity;
import codeping.flex.investment.domain.model.HoldStock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HoldStockPersistenceMapper extends PersistenceMapper<HoldStockEntity, HoldStock> {

}