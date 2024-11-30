package codeping.flex.investment.global.annotation.swagger;

import codeping.flex.investment.domain.exception.HoldStockErrorCode;
import codeping.flex.investment.domain.exception.InvestmentErrorCode;
import codeping.flex.investment.domain.exception.RecentTransactionErrorCode;
import codeping.flex.investment.domain.exception.TransactionErrorCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorCodes {
	InvestmentErrorCode[] investmentErrors() default {};
	RecentTransactionErrorCode[] recentTransactionErrors() default {};
	TransactionErrorCode[] transactionErrors() default {};
	HoldStockErrorCode[] holdStockErrors() default {};
}
