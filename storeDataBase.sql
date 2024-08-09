CREATE TABLE storeTable (
    SID int NOT NULL,
    StoreName varchar(255) NOT NULL,
    PRIMARY KEY (SID)
);

CREATE TABLE shippingCompanyTable (
	SCID int NOT NULL,
	SCName varchar(255) NOT NULL,
	contact varchar(255) NOT NULL,
	shippingfee DECIMAL(10, 2) NOT NULL,
	Contactmobile varchar(255) NOT NULL,
	PRIMARY KEY (SCID)
);

CREATE TABLE StoreShippingCompanyTable (
	SCID int NOT NULL,
	SID int NOT NULL,
	PRIMARY KEY (SCID, SID),
	FOREIGN KEY (SCID) REFERENCES shippingCompanyTable(SCID),
	FOREIGN KEY (SID) REFERENCES storeTable(SID)
);

CREATE TABLE ProductsTable (
	PID VARCHAR(255) NOT NULL,
	productName VARCHAR(255) NOT NULL,
	CostPrice int NOT NULL,
	SellingPrice int NOT NULL,
	weight DECIMAL(10, 2) NOT NULL,
	Stock int NOT NULL CHECK (Stock >= 0),
	PRIMARY KEY (PID)
);

CREATE TABLE WebsiteProductsTable (
	PID VARCHAR(255) NOT NULL,
	productName VARCHAR(255) NOT NULL,
	CostPrice int NOT NULL,
	SellingPrice int NOT NULL,
	DestCountry VARCHAR(255) NOT NULL,
	weight DECIMAL(10, 2) NOT NULL,
	Stock int NOT NULL CHECK (Stock >= 0),
	PRIMARY KEY (PID)
);

CREATE TABLE SoldThroughWebsiteProductsTable (
	PID VARCHAR(255) NOT NULL,
	productName VARCHAR(255) NOT NULL,
	CostPrice int NOT NULL,
	SellingPrice int NOT NULL,
	DestCountry VARCHAR(255),
	weight DECIMAL(10, 2) NOT NULL,
	Stock int NOT NULL CHECK (Stock >= 0),
	PRIMARY KEY (PID),
	FOREIGN KEY (PID) REFERENCES ProductsTable(PID)
);

CREATE TABLE SoldInStoreProductsTable (
	PID VARCHAR(255) NOT NULL,
	productName VARCHAR(255) NOT NULL,
	CostPrice int NOT NULL,
	SellingPrice int NOT NULL,
	weight DECIMAL(10, 2) NOT NULL,
	Stock int NOT NULL CHECK (Stock >= 0),
	PRIMARY KEY (PID),
	FOREIGN KEY (PID) REFERENCES ProductsTable(PID)
);

CREATE TABLE SoldToWholesellersProductsTable (
	PID VARCHAR(255) NOT NULL,
	productName VARCHAR(255) NOT NULL,
	CostPrice int NOT NULL,
	SellingPrice int NOT NULL,
	weight DECIMAL(10, 2) NOT NULL,
	Stock int NOT NULL CHECK (Stock >= 0),
	PRIMARY KEY (PID),
	FOREIGN KEY (PID) REFERENCES ProductsTable(PID)
);

CREATE TABLE StoreProductTable (
	PID VARCHAR(255) NOT NULL,
	SID int NOT NULL,
	PRIMARY KEY (PID, SID),
	FOREIGN KEY (PID) REFERENCES ProductsTable(PID),
	FOREIGN KEY (SID) REFERENCES storeTable(SID)
);

CREATE TABLE OrdersTable (
	OID int NOT NULL,
	quantity int NOT NULL,
	shipmentType VARCHAR(50) NOT NULL,
	PRIMARY KEY (OID),
	CHECK (shipmentType IN ('STANDARD', 'EXPRESS' , 'Without'))
);

CREATE TABLE ShippingOrderTable (
	SCID int NOT NULL,
	OID int NOT NULL,
	PRIMARY KEY (SCID, OID),
	FOREIGN KEY (SCID) REFERENCES shippingCompanyTable(SCID),
	FOREIGN KEY (OID) REFERENCES OrdersTable(OID)	
);

CREATE TABLE CustomerTable (
	CID int NOT NULL,
	CustomerName VARCHAR(255) NOT NULL,
	Mobile VARCHAR(255) NOT NULL,
	PRIMARY KEY (CID)
);

CREATE TABLE OrderCustomerTable (
	OID int NOT NULL,
	CID int NOT NULL,
	PRIMARY KEY (OID, CID),
	FOREIGN KEY (OID) REFERENCES OrdersTable(OID),	
	FOREIGN KEY (CID) REFERENCES CustomerTable(CID)
);