package codeping.flex.investment.adapter.out.persistence.repository;

import codeping.flex.investment.adapter.in.web.data.investment.response.RankingResponse;
import codeping.flex.investment.adapter.out.persistence.entity.TransactionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    Slice<TransactionEntity> findAllByUserId(Long userId, Pageable pageable);

    @Query("SELECT new codeping.flex.investment.adapter.in.web.data.investment.response.RankingResponse(t.userId, u.userInfo.nickname, u.userInfo.blogName, u.userInfo.profileImageUrl, t.totalProfit) " +
            "FROM RecentTransactionEntity rt " +
            "JOIN rt.transaction t " +
            "JOIN UserEntity u ON u.userId = rt.userId " +
            "WHERE EXISTS (" +
            "   SELECT 1 FROM InvestmentEntity i " +
            "   WHERE i.investmentId = t.investment.investmentId " +
            "   AND i.investType = 'SELL' " +
            "   AND u.status = 'ACTIVE' " +
            ") " +
            "ORDER BY t.totalProfit DESC ")
    List<RankingResponse> getRankingOrderByTotalProfit(Pageable pageable);
}
