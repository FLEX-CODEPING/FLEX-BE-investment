--
-- Table structure for table `back_test`
--
DROP TABLE IF EXISTS `back_test`;
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
DROP TABLE IF EXISTS `user`;
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
-- Table structure for table `point`
--
DROP TABLE IF EXISTS `point`;
CREATE TABLE `point` (
                         `created_at` datetime(6) NOT NULL,
                         `modified_at` datetime(6) NOT NULL,
                         `point` bigint NOT NULL,
                         `point_id` bigint NOT NULL AUTO_INCREMENT,
                         `user_id` bigint NOT NULL,
                         `type` varchar(255) NOT NULL,
                         PRIMARY KEY (`point_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `investment`
--
DROP TABLE IF EXISTS `investment`;
CREATE TABLE `investment` (
                              `amount` int NOT NULL COMMENT '매수 수량',
                              `price` decimal(38,2) DEFAULT NULL COMMENT '매수 가격',
                              `profit` decimal(38,2) DEFAULT NULL COMMENT '매도 후 발생한 차익',
                              `total_buy_price` decimal(38,2) DEFAULT NULL COMMENT '매수 총 금액',
                              `created_at` datetime(6) NOT NULL,
                              `investment_id` bigint NOT NULL AUTO_INCREMENT,
                              `modified_at` datetime(6) NOT NULL,
                              `user_id` bigint NOT NULL,
                              `corp_name` varchar(255) DEFAULT NULL,
                              `stock_code` varchar(255) DEFAULT NULL,
                              `invest_type` enum('BUY','SELL') NOT NULL COMMENT '매수/매도 타입 (매수, 매도)',
                              PRIMARY KEY (`investment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `hold_stock`
--
DROP TABLE IF EXISTS `hold_stock`;
CREATE TABLE `hold_stock` (
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
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
                               `balance` decimal(38,2) NOT NULL COMMENT '잔고',
                               `total_profit` decimal(38,2) NOT NULL COMMENT '총 수익',
                               `created_at` datetime(6) NOT NULL,
                               `investment_id` bigint DEFAULT NULL,
                               `modified_at` datetime(6) NOT NULL,
                               `point_id` bigint DEFAULT NULL,
                               `transaction_id` bigint NOT NULL AUTO_INCREMENT,
                               `user_id` bigint NOT NULL,
                               PRIMARY KEY (`transaction_id`),
                               UNIQUE KEY (`investment_id`),
                               UNIQUE KEY (`point_id`),
                               CONSTRAINT FOREIGN KEY (`investment_id`) REFERENCES `investment` (`investment_id`),
                               CONSTRAINT FOREIGN KEY (`point_id`) REFERENCES `point` (`point_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `recent_transaction`
--
DROP TABLE IF EXISTS `recent_transaction`;
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
