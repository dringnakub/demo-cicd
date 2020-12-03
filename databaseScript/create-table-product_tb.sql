create table product_tb
(
	product_id int auto_increment,
	product_name varchar(128) null,
	price DECIMAL(10,3) null,
	brand varchar(64) null,
	gender varchar(16) null,
	age int null,
	quantity int null,
	img varchar(512) null,
	constraint product_tb_pk
		primary key (product_id)
);