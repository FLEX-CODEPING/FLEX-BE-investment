ALTER TABLE hold_stock
    ADD COLUMN avg_price DECIMAL(38, 6) NOT NULL COMMENT '평단가' AFTER hold_status,
    ADD COLUMN principal DECIMAL(38, 6) NOT NULL COMMENT '원금' AFTER avg_price;