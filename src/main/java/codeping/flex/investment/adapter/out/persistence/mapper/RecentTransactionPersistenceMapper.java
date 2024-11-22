package codeping.flex.investment.adapter.out.persistence.mapper;

import codeping.flex.investment.adapter.out.persistence.entity.stockportfolio.RecentTransactionEntity;
import codeping.flex.investment.domain.model.stockportfolio.RecentTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecentTransactionPersistenceMapper extends PersistenceMapper<RecentTransactionEntity, RecentTransaction> {

}
