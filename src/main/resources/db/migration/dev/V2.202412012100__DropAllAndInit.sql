-- Drop all tables
SET foreign_key_checks = 0;
DROP TABLE IF EXISTS flex_investment.user;
DROP TABLE IF EXISTS flex_investment.back_test;
DROP TABLE IF EXISTS flex_investment.transaction;
DROP TABLE IF EXISTS flex_investment.recent_transaction;
DROP TABLE IF EXISTS flex_investment.investment;
DROP TABLE IF EXISTS flex_investment.hold_stock;
DROP TABLE IF EXISTS flex_investment.point;
DROP TABLE IF EXISTS flex_investment.credit;

-- Initialize all tables
SET foreign_key_checks = 0;

--
-- Table structure for table `back_test`
--
CREATE TABLE `back_test` (
                             `amount` int NOT NULL,
                             `end_date` date NOT NULL,
                             `result` decimal(38,2) NOT NULL,
                             `start_date` date NOT NULL,
                             `total_price` decimal(38,2) NOT NULL,
                             `back_test_id` bigint NOT NULL AUTO_INCREMENT,
                             `created_at` datetime(6) NOT NULL,
                             `modified_at` datetime(6) NOT NULL,
                             `user_id` bigint NOT NULL,
                             `corp_name` varchar(255) NOT NULL,
                             `period_type` varchar(255) DEFAULT NULL,
                             `stock_code` varchar(255) NOT NULL,
                             `invest_type` enum('BUY','SELL') DEFAULT NULL,
                             PRIMARY KEY (`back_test_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `user`
--
CREATE TABLE `user` (
                        `birth` date DEFAULT NULL,
                        `is_birth_visible` bit(1) DEFAULT NULL,
                        `is_salary_visible` bit(1) DEFAULT NULL,
                        `created_at` datetime(6) NOT NULL,
                        `modified_at` datetime(6) NOT NULL,
                        `social_id` bigint NOT NULL,
                        `user_id` bigint NOT NULL AUTO_INCREMENT,
                        `blog_name` varchar(255) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `nickname` varchar(255) DEFAULT NULL,
                        `notification_email` varchar(255) DEFAULT NULL,
                        `profile_image_url` varchar(255) DEFAULT NULL,
                        `salary` enum('LESS_100K','LESS_150K','LESS_200K','LESS_3K','LESS_5K','LESS_8K','OVER_200K') DEFAULT NULL,
                        `status` enum('ACTIVE','PENDING','WITHDRAW') NOT NULL,
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY (`social_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `credit`
--
CREATE TABLE `credit` (
                          `created_at` datetime(6) NOT NULL,
                          `credit_id` bigint NOT NULL AUTO_INCREMENT,
                          `credits` bigint NOT NULL,
                          `modified_at` datetime(6) NOT NULL,
                          `user_id` bigint NOT NULL,
                          `credit_type` varchar(255) NOT NULL,
                          PRIMARY KEY (`credit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `investment`
--
CREATE TABLE `investment` (
                              `price` decimal(38,2) DEFAULT NULL COMMENT '매매 가격',
                              `profit` decimal(38,2) DEFAULT NULL COMMENT '매매 차익',
                              `quantity` int NOT NULL COMMENT '매매 수량',
                              `total_price` decimal(38,2) DEFAULT NULL COMMENT '매매 총 금액',
                              `created_at` datetime(6) NOT NULL,
                              `investment_id` bigint NOT NULL AUTO_INCREMENT,
                              `modified_at` datetime(6) NOT NULL,
                              `user_id` bigint NOT NULL,
                              `corp_name` varchar(255) DEFAULT NULL,
                              `stock_code` varchar(255) DEFAULT NULL,
                              `invest_type` enum('BUY','SELL') NOT NULL COMMENT '매매 타입 (매수, 매도)',
                              PRIMARY KEY (`investment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `hold_stock`
--
CREATE TABLE `hold_stock` (
                              `avg_price` decimal(38,6) NOT NULL COMMENT '평단가',
                              `principal` decimal(38,6) NOT NULL COMMENT '원금',
                              `created_at` datetime(6) NOT NULL,
                              `hold_stock_id` bigint NOT NULL AUTO_INCREMENT,
                              `modified_at` datetime(6) NOT NULL,
                              `recent_investment_id` bigint NOT NULL,
                              `total_holdings` bigint NOT NULL COMMENT '총 보유량',
                              `user_id` bigint NOT NULL,
                              `corp_name` varchar(255) NOT NULL,
                              `stock_code` varchar(255) NOT NULL,
                              `hold_status` enum('HOLDING','SOLD') NOT NULL COMMENT '보유 상태',
                              PRIMARY KEY (`hold_stock_id`),
                              UNIQUE KEY (`recent_investment_id`),
                              CONSTRAINT FOREIGN KEY (`recent_investment_id`) REFERENCES `investment` (`investment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `transaction`
--
CREATE TABLE `transaction` (
                               `balance` decimal(38,2) NOT NULL COMMENT '잔고',
                               `total_profit` decimal(38,6) NOT NULL COMMENT '총 수익',
                               `created_at` datetime(6) NOT NULL,
                               `credit_id` bigint DEFAULT NULL,
                               `investment_id` bigint DEFAULT NULL,
                               `modified_at` datetime(6) NOT NULL,
                               `transaction_id` bigint NOT NULL AUTO_INCREMENT,
                               `user_id` bigint NOT NULL,
                               PRIMARY KEY (`transaction_id`),
                               UNIQUE KEY (`investment_id`),
                               UNIQUE KEY (`credit_id`),
                               CONSTRAINT FOREIGN KEY (`investment_id`) REFERENCES `investment` (`investment_id`),
                               CONSTRAINT FOREIGN KEY (`credit_id`) REFERENCES `credit` (`credit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `recent_transaction`
--
CREATE TABLE `recent_transaction` (
                                      `created_at` datetime(6) NOT NULL,
                                      `modified_at` datetime(6) NOT NULL,
                                      `recent_transaction_at` datetime(6) NOT NULL,
                                      `recent_transaction_id` bigint NOT NULL AUTO_INCREMENT,
                                      `transaction_id` bigint NOT NULL,
                                      `user_id` bigint NOT NULL,
                                      PRIMARY KEY (`recent_transaction_id`),
                                      UNIQUE KEY (`transaction_id`),
                                      CONSTRAINT FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
