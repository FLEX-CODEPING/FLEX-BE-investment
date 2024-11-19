package codeping.flex.investment.adapter.out.persistence.mapper;

import codeping.flex.investment.adapter.out.persistence.entity.transaction.HoldStockEntity;
import codeping.flex.investment.adapter.out.persistence.entity.transaction.TransactionEntity;
import codeping.flex.investment.domain.model.HoldStock;
import codeping.flex.investment.domain.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = InvestmentPersistenceMapper.class)
public interface TransactionPersistenceMapper {

    TransactionPersistenceMapper INSTANCE = Mappers.getMapper(TransactionPersistenceMapper.class);

    @Mapping(target = "investmentEntity", source = "investment")
    TransactionEntity toEntity(Transaction transaction);

    @Mapping(target = "investment", source = "investment")
    Transaction toDomain(TransactionEntity entity);
}