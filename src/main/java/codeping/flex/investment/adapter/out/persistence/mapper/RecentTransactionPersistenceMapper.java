package codeping.flex.investment.adapter.out.persistence.mapper;

import codeping.flex.investment.adapter.out.persistence.entity.stockportfolio.RecentTransactionEntity;
import codeping.flex.investment.domain.model.stockportfolio.RecentTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = InvestmentPersistenceMapper.class)
public interface RecentTransactionPersistenceMapper {

    RecentTransactionPersistenceMapper INSTANCE = Mappers.getMapper(RecentTransactionPersistenceMapper.class);

    RecentTransactionEntity toRecentTransactionEntity(RecentTransaction recentTransaction);
    RecentTransaction toRecentTransaction(RecentTransactionEntity entity);
}
