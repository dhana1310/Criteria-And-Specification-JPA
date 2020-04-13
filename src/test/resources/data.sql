INSERT INTO brands(brand_id,brand_name) VALUES(1,'Electra');
INSERT INTO brands(brand_id,brand_name) VALUES(2,'Haro');
INSERT INTO brands(brand_id,brand_name) VALUES(3,'Heller');
INSERT INTO brands(brand_id,brand_name) VALUES(4,'Pure Cycles');
INSERT INTO brands(brand_id,brand_name) VALUES(5,'Ritchey');
INSERT INTO brands(brand_id,brand_name) VALUES(6,'Strider');
INSERT INTO brands(brand_id,brand_name) VALUES(7,'Sun Bicycles');
INSERT INTO brands(brand_id,brand_name) VALUES(8,'Surly');
INSERT INTO brands(brand_id,brand_name) VALUES(9,'Trek');

INSERT INTO categories(category_id,category_name) VALUES(1,'Children Bicycles');
INSERT INTO categories(category_id,category_name) VALUES(2,'Comfort Bicycles');
INSERT INTO categories(category_id,category_name) VALUES(3,'Cruisers Bicycles');
INSERT INTO categories(category_id,category_name) VALUES(4,'Cyclocross Bicycles');
INSERT INTO categories(category_id,category_name) VALUES(5,'Electric Bikes');
INSERT INTO categories(category_id,category_name) VALUES(6,'Mountain Bikes');
INSERT INTO categories(category_id,category_name) VALUES(7,'Road Bikes');

INSERT INTO products(product_id, product_name, brand_id, category_id, model_year, list_price) VALUES(1,'Trek 820 - 2016',9,6,2016,379.99);
INSERT INTO products(product_id, product_name, brand_id, category_id, model_year, list_price) VALUES(2,'Ritchey Timberwolf Frameset - 2016',5,6,2016,749.99);
INSERT INTO products(product_id, product_name, brand_id, category_id, model_year, list_price) VALUES(3,'Surly Wednesday Frameset - 2016',8,6,2016,999.99);
INSERT INTO products(product_id, product_name, brand_id, category_id, model_year, list_price) VALUES(4,'Trek Fuel EX 8 29 - 2016',9,6,2016,2899.99);
INSERT INTO products(product_id, product_name, brand_id, category_id, model_year, list_price) VALUES(5,'Heller Shagamaw Frame - 2016',3,6,2016,1320.99);

INSERT INTO customers(first_name, last_name, phone, email, street, city, state, zip_code) VALUES('Debra','Burks',NULL,'debra.burks@yahoo.com','9273 Thorne Ave. ','Orchard Park','NY',14127);
INSERT INTO customers(first_name, last_name, phone, email, street, city, state, zip_code) VALUES('Kasha','Todd',NULL,'kasha.todd@yahoo.com','910 Vine Street ','Campbell','CA',95008);
INSERT INTO customers(first_name, last_name, phone, email, street, city, state, zip_code) VALUES('Tameka','Fisher',NULL,'tameka.fisher@aol.com','769C Honey Creek St. ','Redondo Beach','CA',90278);
INSERT INTO customers(first_name, last_name, phone, email, street, city, state, zip_code) VALUES('Daryl','Spence',NULL,'daryl.spence@aol.com','988 Pearl Lane ','Uniondale','NY',11553);

INSERT INTO stores(store_name, phone, email, street, city, state, zip_code) VALUES
('Santa Cruz Bikes','(831) 476-4321','santacruz@bikes.shop','3700 Portola Drive', 'Santa Cruz','CA',95060),
('Baldwin Bikes','(516) 379-8888','baldwin@bikes.shop','4200 Chestnut Lane', 'Baldwin','NY',11432),
('Rowlett Bikes','(972) 530-5555','rowlett@bikes.shop','8000 Fairway Avenue', 'Rowlett','TX',75088);

-- stocks
INSERT INTO stocks(store_id, product_id, quantity) VALUES(1,1,27);
INSERT INTO stocks(store_id, product_id, quantity) VALUES(2,2,5);
INSERT INTO stocks(store_id, product_id, quantity) VALUES(3,3,6);
INSERT INTO stocks(store_id, product_id, quantity) VALUES(1,4,23);

INSERT INTO staffs(staff_id, first_name, last_name, email, phone, active, store_id, manager_id) VALUES(1,'Fabiola','Jackson','fabiola.jackson@bikes.shop','(831) 555-5554',1,1,NULL);
INSERT INTO staffs(staff_id, first_name, last_name, email, phone, active, store_id, manager_id) VALUES(2,'Mireya','Copeland','mireya.copeland@bikes.shop','(831) 555-5555',1,2,1);
INSERT INTO staffs(staff_id, first_name, last_name, email, phone, active, store_id, manager_id) VALUES(3,'Genna','Serrano','genna.serrano@bikes.shop','(831) 555-5556',1,3,2);
INSERT INTO staffs(staff_id, first_name, last_name, email, phone, active, store_id, manager_id) VALUES(4,'Virgie','Wiggins','virgie.wiggins@bikes.shop','(831) 555-5557',1,1,2);

INSERT INTO orders(order_id, customer_id, order_status, order_date, required_date, shipped_date, store_id,staff_id) VALUES(1,1,4,'20160101','20160103','20160103',1,2);
INSERT INTO orders(order_id, customer_id, order_status, order_date, required_date, shipped_date, store_id,staff_id) VALUES(2,2,4,'20160101','20160104','20160103',2,1);
INSERT INTO orders(order_id, customer_id, order_status, order_date, required_date, shipped_date, store_id,staff_id) VALUES(3,3,4,'20160102','20160105','20160103',2,4);
INSERT INTO orders(order_id, customer_id, order_status, order_date, required_date, shipped_date, store_id,staff_id) VALUES(4,4,4,'20160103','20160104','20160105',1,3);

INSERT INTO order_items(order_id, item_id, product_id, quantity, list_price,discount) VALUES(1,1,2,1,599.99,0.2);
INSERT INTO order_items(order_id, item_id, product_id, quantity, list_price,discount) VALUES(2,2,3,2,1799.99,0.07);
INSERT INTO order_items(order_id, item_id, product_id, quantity, list_price,discount) VALUES(1,3,1,2,1549.00,0.05);
INSERT INTO order_items(order_id, item_id, product_id, quantity, list_price,discount) VALUES(3,4,5,2,599.99,0.05);