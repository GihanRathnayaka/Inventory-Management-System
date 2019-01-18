CREATE TABLE RawMaterial(
  rowMatID VARCHAR(20) NOT NULL UNIQUE PRIMARY KEY ,
  name VARCHAR(50)not null unique ,
  regDate DATE not null ,
  aviableQTY DECIMAL(18,2),
  descriptiin VARCHAR(60)
);

CREATE TABLE MterialReceive(
  receiveID VARCHAR(25)NOT NULL PRIMARY KEY ,
  receiveDate DATE not null ,
  description VARCHAR(100),
  total decimal(18,2) not null,
  supplierID VARCHAR(20),
 foreign key (supplierID) references supplier(supplierID)
);

CREATE TABLE MatReceiveDetails(
  id int  unique primary key auto_increment,
  madeDate date not null,
  expireDate date not null,
  price decimal(18,2) not null ,
  Qty decimal(18,2) not null,
  aviableQty DECIMAL(18,2),
  receiveID VARCHAR(25),
  rowMatID varchar(20),
  foreign key (receiveID) references MterialReceive(receiveID),
  foreign key (rowMatID) references RawMaterial(rowMatID)

);
CREATE TABLE MaterialRelease(
  releaseID VARCHAR(25) not null unique primary key ,
  releaseDate date not null ,
  totalValue decimal(18,2) not null,
  description VARCHAR(50)

);

CREATE TABLE MaterialReleaseDetails(
  matRdID varchar(20) not null unique primary key ,
  releaseqty decimal (18,2)not null ,
  total decimal(18,2)not null,
  releaseID VARCHAR(25),
  rowMatID VARCHAR(20),
  foreign key (releaseID) references materialrelease(releaseID),
  foreign key (rowMatID) references rawmaterial(rowMatID)

);
CREATE TABLE Fifo(
  fid int not null unique primary key ,
  price decimal(18,2)NOT NULL ,
  reQTy decimal (18,2) not null ,
  totalvalue decimal(18,2)not null ,
  id int ,
  matRdID VARCHAR(20),
  foreign key (id)references materialreceivedetails(id),
  foreign key ( matRdID)references MaterialReleaseDetails(matRdID)

);

CREATE TABLE Product(
  proID VARCHAR(20)NOT NULL UNIQUE PRIMARY KEY ,
  name VARCHAR(50)NOT NULL ,
  req_Date DATE NOT NULL ,
  description VARCHAR(80),
  AviQty DECIMAL(18,2)
);

CREATE TABLE ProductionDetails(
  pdID  int not null UNIQUE AUTO_INCREMENT PRIMARY KEY ,
  recordDate date not null ,
  bellSheetNo VARCHAR(50)not null ,
  nofPackage DECIMAL(18,2) NOT NULL ,
  packagePrice DECIMAL(18,2) NOT NULL ,
  packageUnite DECIMAL(18,2) NOT NULL ,
  total  DECIMAL(18,2)NOT NULL ,
  madeDate DATE NOT NULL ,
  Expire DATE NOT NULL ,
  packType VARCHAR(10) NOT NULL ,
  Description VARCHAR(100),
  proID varchar(20),
  releaseID varchar(25),
  foreign key (proID) REFERENCES product(proID),
  FOREIGN KEY (releaseID) references materialrelease(releaseID)

);