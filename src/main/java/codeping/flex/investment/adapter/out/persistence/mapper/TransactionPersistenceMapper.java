package codeping.flex.investment.adapter.out.persistence.mapper;

import codeping.flex.investment.adapter.out.persistence.entity.stockportfolio.TransactionEntity;
import codeping.flex.investment.domain.model.stockportfolio.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionPersistenceMapper extends PersistenceMapper<TransactionEntity, Transaction> {

}