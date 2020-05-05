INSERT INTO user_status(user_status_code,user_status_description) VALUES('Active', 'Active');
INSERT INTO user_status(user_status_code,user_status_description) VALUES('Inactive', 'Inactive');
INSERT INTO user_status(user_status_code,user_status_description) VALUES('TempLock', 'Temporary disabled/locked');

INSERT INTO order_status(order_status_code,order_status_description) VALUES('OrdAccept', 'Order Accepted');
INSERT INTO order_status(order_status_code,order_status_description) VALUES('OrdConfirm', 'Order Confirmed');
INSERT INTO order_status(order_status_code,order_status_description) VALUES('PreDisptch', 'Preparing for dispatch');
INSERT INTO order_status(order_status_code,order_status_description) VALUES('Dispatched', 'Item dispatched');
INSERT INTO order_status(order_status_code,order_status_description) VALUES('InTransit', 'Item in - transit');
INSERT INTO order_status(order_status_code,order_status_description) VALUES('Completed', 'Order Delivered successfully');
INSERT INTO order_status(order_status_code,order_status_description) VALUES('Cancelled', 'Order Cancelled');
INSERT INTO order_status(order_status_code,order_status_description) VALUES('Hold', 'Order on Hold due to external factors');

INSERT INTO brands(brand_code,brand_description) VALUES('Electra', 'Electra');
INSERT INTO brands(brand_code,brand_description) VALUES('Hero', 'Hero');
INSERT INTO brands(brand_code,brand_description) VALUES('Heller', 'Heller');
INSERT INTO brands(brand_code,brand_description) VALUES('PureCycles', 'Pure Cycles');
INSERT INTO brands(brand_code,brand_description) VALUES('Ritchey', 'Ritchey');
INSERT INTO brands(brand_code,brand_description) VALUES('Strider', 'Strider');
INSERT INTO brands(brand_code,brand_description) VALUES('SunBicycle', 'Sun Bicycles');
INSERT INTO brands(brand_code,brand_description) VALUES('Surly', 'Surly');
INSERT INTO brands(brand_code,brand_description) VALUES('Trek', 'Trek');

INSERT INTO categories(category_code,category_description) VALUES('ChildrenBC', 'Children Bicycles');
INSERT INTO categories(category_code,category_description) VALUES('ComfortBC', 'Comfort Bicycles');
INSERT INTO categories(category_code,category_description) VALUES('CruisersBC', 'Cruisers Bicycles');
INSERT INTO categories(category_code,category_description) VALUES('CyclocrsBC', 'Cyclocross Bicycles');
INSERT INTO categories(category_code,category_description) VALUES('ElectricBI', 'Electric Bikes');
INSERT INTO categories(category_code,category_description) VALUES('MountainBI', 'Mountain Bikes');
INSERT INTO categories(category_code,category_description) VALUES('RoadBI', 'Road Bikes');

INSERT INTO products(product_id, product_name, brand_code, category_code, model_year, list_price) VALUES(1,'Trek 820 - 2016','Trek','MountainBI',2016,379.99);
INSERT INTO products(product_id, product_name, brand_code, category_code, model_year, list_price) VALUES(2,'Ritchey Timberwolf Frameset - 2016','Ritchey','MountainBI',2016,749.99);
INSERT INTO products(product_id, product_name, brand_code, category_code, model_year, list_price) VALUES(3,'Surly Wednesday Frameset - 2016','Surly','MountainBI',2016,999.99);
INSERT INTO products(product_id, product_name, brand_code, category_code, model_year, list_price) VALUES(4,'Trek Fuel EX 8 29 - 2016','Trek','MountainBI',2016,2899.99);
INSERT INTO products(product_id, product_name, brand_code, category_code, model_year, list_price) VALUES(5,'Heller Shagamaw Frame - 2016','Heller','MountainBI',2016,1320.99);

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

INSERT INTO staffs(staff_id, first_name, last_name, email, phone, user_status_code, store_id, manager_id) VALUES(1,'Fabiola','Jackson','fabiola.jackson@bikes.shop','(831) 555-5554','Active',1,NULL);
INSERT INTO staffs(staff_id, first_name, last_name, email, phone, user_status_code, store_id, manager_id) VALUES(2,'Mireya','Copeland','mireya.copeland@bikes.shop','(831) 555-5555','Active',1,1);
INSERT INTO staffs(staff_id, first_name, last_name, email, phone, user_status_code, store_id, manager_id) VALUES(3,'Genna','Serrano','genna.serrano@bikes.shop','(831) 555-5556','Active',1,2);
INSERT INTO staffs(staff_id, first_name, last_name, email, phone, user_status_code, store_id, manager_id) VALUES(4,'Virgie','Wiggins','virgie.wiggins@bikes.shop','(831) 555-5557','Active',1,2);

INSERT INTO orders(order_id, customer_id, order_status_code, order_date, required_date, shipped_date, store_id,staff_id) VALUES(1,1,'Completed','20160101','20160103','20160103',1,2);
INSERT INTO orders(order_id, customer_id, order_status_code, order_date, required_date, shipped_date, store_id,staff_id) VALUES(2,2,'Completed','20160101','20160104','20160103',2,1);
INSERT INTO orders(order_id, customer_id, order_status_code, order_date, required_date, shipped_date, store_id,staff_id) VALUES(3,3,'Completed','20160102','20160105','20160103',2,4);
INSERT INTO orders(order_id, customer_id, order_status_code, order_date, required_date, shipped_date, store_id,staff_id) VALUES(4,4,'Completed','20160103','20160104','20160105',1,3);

INSERT INTO order_items(order_id, item_id, product_id, quantity, list_price,discount) VALUES(1,1,2,1,599.99,0.2);
INSERT INTO order_items(order_id, item_id, product_id, quantity, list_price,discount) VALUES(2,2,3,2,1799.99,0.07);
INSERT INTO order_items(order_id, item_id, product_id, quantity, list_price,discount) VALUES(1,3,1,2,1549.00,0.05);
INSERT INTO order_items(order_id, item_id, product_id, quantity, list_price,discount) VALUES(3,4,5,2,599.99,0.05);