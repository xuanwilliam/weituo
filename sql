create database if not exists stock;
use stock;
create table if not exists f_stock(
	id varchar(36) primary key,
	code char(6),
	name varchar(100)
);

create table if not exists f_stock_record(
	id varchar(36) primary key,
	stock_id varchar(36),
	lastest_price float,
	price_range float,
	y_final_price float,
	t_opening_price float,
	t_top_price float,
	t_bottom_price float,
	trade_volumn float,
	turnover float,
	handover float,
	swing float,
	amount_radio float
);

