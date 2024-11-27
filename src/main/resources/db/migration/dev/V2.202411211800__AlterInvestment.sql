ALTER TABLE `investment` CHANGE COLUMN amount quantity INT NOT NULL COMMENT '매매 수량';
ALTER TABLE `investment` MODIFY COLUMN price DECIMAL(38, 2) COMMENT '매매 가격';
ALTER TABLE `investment` MODIFY COLUMN total_buy_price DECIMAL(38, 2) COMMENT '매매 총 금액';