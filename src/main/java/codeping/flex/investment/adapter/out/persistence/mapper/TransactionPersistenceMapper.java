package codeping.flex.investment.adapter.out.persistence.mapper;

import codeping.flex.investment.adapter.out.persistence.entity.stockportfolio.TransactionEntity;
import codeping.flex.investment.domain.model.stockportfolio.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = InvestmentPersistenceMapper.class)
public interface TransactionPersistenceMapper extends EntityMapper<TransactionEntity, Transaction> {

    TransactionPersistenceMapper INSTANCE = Mappers.getMapper(TransactionPersistenceMapper.class);
}