CREATE TABLE IF NOT EXISTS `author`(
    author_name VARCHAR(20) PRIMARY KEY,
    nationality VARCHAR(20) NOT NULL,
    birth YEAR NOT NULL,
    author_description VARCHAR(100),
    pub_sum INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `category`(
    category_name VARCHAR(20) PRIMARY KEY,
    sum INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `press`(
    press_name VARCHAR(20) PRIMARY KEY,
    location VARCHAR(200) NOT NULL,
    all_sum INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `book`(
    ISBN VARCHAR(13) PRIMARY KEY,
    book_name VARCHAR(100) NOT NULL,
    book_description VARCHAR(200),
    time YEAR NOT NULL,
    comment_num INT NOT NULL,
    pos_rate DECIMAL(4,3),
    press_name VARCHAR(20) NOT NULL,
    author_name VARCHAR(20) NOT NULL,
    category_name VARCHAR(20) NOT NULL,
    price DECIMAL(8,2) NOT NULL,
    CONSTRAINT fk_book_press
        FOREIGN KEY(press_name)
        REFERENCES press(press_name)
        ON DELETE CASCADE,
    CONSTRAINT fk_book_author
        FOREIGN KEY(author_name)
        REFERENCES author(author_name)
        ON DELETE CASCADE,
    CONSTRAINT fk_book_category
        FOREIGN KEY(category_name)
        REFERENCES category(category_name)
        ON DELETE CASCADE
);
CREATE INDEX ind_book ON book(ISBN);

CREATE TABLE IF NOT EXISTS `vip_rate`(
    level INT,
    rate DECIMAL(3,2),
    PRIMARY KEY(level,rate)
);

CREATE TABLE IF NOT EXISTS `customer`(
    customer_id VARCHAR(20) PRIMARY KEY,
    nickname VARCHAR(25) NOT NULL,
    pw VARCHAR(64) NOT NULL,
    mail_id VARCHAR(30),
    telephone VARCHAR(11) NOT NULL,
    level INT,
    purchase_sum DECIMAL(8,2),
    identity VARCHAR(10) NOT NULL,
    current_order_id INT,
    def_location VARCHAR(200),
    CONSTRAINT fk_customer_level
        FOREIGN KEY(level)
        REFERENCES vip_rate(level)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `orders`(
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    update_time DATETIME NOT NULL,
    receipt_place VARCHAR(200) NOT NULL,
    customer_id VARCHAR(20) NOT NULL,
    price_sum DECIMAL(8,2) NOT NULL,
    CONSTRAINT fk_customer_order
        FOREIGN KEY(customer_id)
        REFERENCES customer(customer_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
CREATE INDEX ind_order ON orders(order_id);
ALTER TABLE customer
    ADD CONSTRAINT fk_current
    Foreign Key (current_order_id)
    REFERENCES orders(order_id)
    ON DELETE CASCADE;

CREATE TABLE IF NOT EXISTS `order_book`(
    ISBN VARCHAR(13),
    order_id INT,
    order_sum INT NOT NULL,
    PRIMARY KEY(order_id,ISBN),
    CONSTRAINT fk_ob1
        FOREIGN KEY(order_id)
        REFERENCES orders(order_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_ob2
        FOREIGN KEY(ISBN)
        REFERENCES book(ISBN)
        ON DELETE CASCADE
);

