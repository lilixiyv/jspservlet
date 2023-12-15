-- 防止因为外键的问题报错
SET FOREIGN_KEY_CHECKS = 0;

-- 开始删除
DROP TABLE author;
DROP TABLE book;
DROP TABLE category;
DROP TABLE customer;
DROP TABLE order_book;
DROP TABLE orders;
DROP TABLE press;
DROP TABLE vip_rate;

-- 恢复外键检查
SET FOREIGN_KEY_CHECKS = 1;