package codeping.flex.investment.adapter.out.persistence.mapper;

import codeping.flex.investment.adapter.out.persistence.entity.RecentTransactionEntity;
import codeping.flex.investment.domain.model.RecentTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecentTransactionPersistenceMapper extends PersistenceMapper<RecentTransactionEntity, RecentTransaction> {

}
