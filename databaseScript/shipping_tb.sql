CREATE TABLE db.shipping_tb (
	shipping_id int auto_increment NOT NULL,
	shipping_rate int NULL,
	shipping_name varchar(100) NULL,
	CONSTRAINT shipping_tb_PK PRIMARY KEY (shipping_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO db.shipping_tb (shipping_rate, shipping_name) VALUES(40, 'Kerry');
