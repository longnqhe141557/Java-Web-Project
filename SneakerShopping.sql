CREATE DATABASE SneakerShopping

CREATE TABLE Administrator (
   adminID INT IDENTITY(1,1) PRIMARY KEY,
   username NVARCHAR(50),
   pass NVARCHAR(50)
);

INSERT INTO Administrator 
VALUES ('nql14032000', 'longdeptrai123');

CREATE TABLE Category (
   cateID INT IDENTITY(1,1) PRIMARY KEY,
   cateName NVARCHAR(100),
   status INT
);

INSERT INTO Category
VALUES ('Air Max', 1);
INSERT INTO Category
VALUES ('Jordan 1', 1);
INSERT INTO Category
VALUES ('Nike Dunk', 1);
SELECT * FROM Category

CREATE TABLE Product (
   pid NVARCHAR(50),
   pname NVARCHAR(50),
   quantity INT,
   price MONEY,
   image NVARCHAR(50),
   status INT,
   cateID INT
);

INSERT INTO Product
VALUES ('CU1816-100', 'Air Max 90 Bacon', 10, 140, 'image/airmax1.jpg', 1, 1);
INSERT INTO Product
VALUES ('CT1685-100', 'Air Max 90 Infrared', 10, 140, 'image/airmax2.jpg', 1, 1);
INSERT INTO Product
VALUES ('CD0917-600', 'Air Max 90 Viotech', 10, 140, 'image/airmax3.jpg', 1, 1);
INSERT INTO Product
VALUES ('CZ5594-100', 'Air Max 90 White', 10, 140, 'image/airmax4.jpg', 1, 1);

INSERT INTO Product
VALUES ('DD1503-101', 'Nike Dunk Low Black', 10, 100, 'image/dunkblack.jpg', 1, 3);
INSERT INTO Product
VALUES ('CU1726-100', 'Nike Dunk Kentucky', 10, 100, 'image/dunkkentucky.jpg', 1, 3);
INSERT INTO Product
VALUES ('DD1391-700', 'Nike Dunk Low Michigan', 10, 100, 'image/dunklowmichigan.jpg', 1, 3);
INSERT INTO Product
VALUES ('CU1726-101', 'Nike Dunk Syracuse', 10, 100, 'image/dunksyracuse.jpg', 1, 3);

INSERT INTO Product
VALUES ('555088-700', 'Air Jordan 1 ROY', 10, 160, 'image/jordan1.jpg', 1, 2);
INSERT INTO Product
VALUES ('555088-140', 'Air Jordan 1 Obsidian', 10, 160, 'image/jordan2.jpg', 1, 2);
INSERT INTO Product
VALUES ('555088-701', 'Air Jordan 1 Mocha', 10, 160, 'image/jordan3.jpg', 1, 2);
INSERT INTO Product
VALUES ('555088-702', 'Air Jordan 1 Blue', 10, 160, 'image/jordan4.jpg', 1, 2);
SELECT * FROM Product
ORDER BY cateID

ALTER TABLE Product
ALTER COLUMN pid NVARCHAR(50) NOT NULL;

ALTER TABLE Product
ADD PRIMARY KEY (pid);

CREATE TABLE Customer (
   cid INT IDENTITY(1,1) PRIMARY KEY,
   cname NVARCHAR(50),
   cphone NVARCHAR(50),
   cAddress NVARCHAR(50),
   username NVARCHAR(50),
   pass NVARCHAR(50),
);

INSERT INTO Customer
VALUES('Nguyen Quang Long', '0975310760', 'Ha Noi', 'longdeptrai123', 'longdeptrai123');

CREATE TABLE Bill (
   oID NVARCHAR(50) NOT NULL PRIMARY KEY,
   dateCreate DATETIME,
   cname NVARCHAR(50),
   cphone NVARCHAR(50),
   cAddress NVARCHAR(50),
   Total MONEY,
   status INT,
   cid INT,
);

CREATE TABLE BillDetail (
  pid NVARCHAR(50),
  oID NVARCHAR(50),
  quantity INT,
  price MONEY,
  total MONEY
);