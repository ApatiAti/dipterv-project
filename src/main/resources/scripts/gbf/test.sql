--
-- Table structure for table  appointment 
--

CREATE TABLE  appointment  (
   id  bigint(20) NOT NULL,
   complaints  longtext,
   consultationHourId  bigint(20) DEFAULT NULL,
   patientId  bigint(20) DEFAULT NULL,
  PRIMARY KEY ( id ),
  KEY  FK_4ebl9nenv1fvfkd701kkgkm7n  ( consultationHourId ),
  KEY  FK_csvqd5dbp74ksv9ulxqhxh8tl  ( patientId ),
  CONSTRAINT  FK_4ebl9nenv1fvfkd701kkgkm7n  FOREIGN KEY ( consultationHourId ) REFERENCES  consultationhour  ( id ),
  CONSTRAINT  FK_csvqd5dbp74ksv9ulxqhxh8tl  FOREIGN KEY ( patientId ) REFERENCES  user  ( id )
);

--
-- Dumping data for table  appointment 
--

INSERT INTO  appointment  VALUES (1,'Fáj a szemem',10000,1);

--
-- Table structure for table  consultationhour 
--

CREATE TABLE  consultationhour  (
   id  bigint(20) NOT NULL,
   beginDate  datetime NOT NULL,
   endDate  datetime NOT NULL,
   maxNumberOfPatient  int(11) NOT NULL,
   room  varchar(255) NOT NULL,
   departmentId  bigint(20) NOT NULL,
   doctorId  bigint(20) NOT NULL,
   consultationhour_tpyeid  bigint(20) NOT NULL,
  PRIMARY KEY ( id ),
  KEY  FK_nayp8fp0247jx0klf2xo5tu5f  ( departmentId ),
  KEY  FK_9mjm21mex8lbtig2708niw4u  ( doctorId ),
  KEY  FK_5l5bjgiha6hg7yvvf3k84y7j6  ( consultationhour_tpyeid ),
  CONSTRAINT  FK_5l5bjgiha6hg7yvvf3k84y7j6  FOREIGN KEY ( consultationhour_tpyeid ) REFERENCES  consultationhour_type  ( id ),
  CONSTRAINT  FK_9mjm21mex8lbtig2708niw4u  FOREIGN KEY ( doctorId ) REFERENCES  user  ( id ),
  CONSTRAINT  FK_nayp8fp0247jx0klf2xo5tu5f  FOREIGN KEY ( departmentId ) REFERENCES  department  ( id )
);

--
-- Dumping data for table  consultationhour 
--

INSERT INTO  consultationhour  VALUES (10000,'2017-06-01 16:00:00','2017-06-01 16:00:00',7,'RD41',10000,3,1);
INSERT INTO  consultationhour  VALUES (10001,'2017-05-10 16:00:00','2017-05-10 16:00:00',7,'RD41',10000,4,4);
INSERT INTO  consultationhour  VALUES (10002,'2015-06-01 16:00:00','2015-06-01 16:00:00',7,'RD41',10000,3,1);

--
-- Table structure for table  consultationhour_type 
--

CREATE TABLE  consultationhour_type  (
   id  bigint(20) NOT NULL,
   name  varchar(255) DEFAULT NULL,
   DEPARTMENTID  bigint(20) NOT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  UK_82jgcotlohv6idsosms8fq1w0  ( DEPARTMENTID , name ),
  CONSTRAINT  FK_dxny9353jwhwhs4llndf9dl48  FOREIGN KEY ( DEPARTMENTID ) REFERENCES  department  ( id )
);


--
-- Dumping data for table  consultationhour_type 
--

INSERT INTO  consultationhour_type  VALUES (2,'Látás vizsgélat',10000);
INSERT INTO  consultationhour_type  VALUES (3,'Szürkehályog vizsg?álat',10000);
INSERT INTO  consultationhour_type  VALUES (1,'Szem műtét',10000);
INSERT INTO  consultationhour_type  VALUES (4,'Általános vizsgálat',10001);
INSERT INTO  consultationhour_type  VALUES (5,'Lúdtalp vizsgálat',10001);
INSERT INTO  consultationhour_type  VALUES (6,'Protézis műtét',10001);

--
-- Table structure for table  department 
--

CREATE TABLE  department  (
   id  bigint(20) NOT NULL,
   description  longtext NOT NULL,
   name  varchar(255) NOT NULL,
   phoneNumber  varchar(12) NOT NULL,
   place  varchar(255) NOT NULL,
   departmentHead  bigint(20) NOT NULL,
  PRIMARY KEY ( id ),
  KEY  FK_lgmat3uhaph3jsatcdv235pfo  ( departmentHead ),
  CONSTRAINT  FK_lgmat3uhaph3jsatcdv235pfo  FOREIGN KEY ( departmentHead ) REFERENCES  user  ( id )
);


--
-- Dumping data for table  department 
--

INSERT INTO  department  VALUES (10000,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','Szemészet','36305248967','IB025',3);
INSERT INTO  department  VALUES (10001,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','Ortopédia','36305345967','IB025',4);

--
-- Table structure for table  department_to_user 
--

CREATE TABLE  department_to_user  (
   idDepartment  bigint(20) NOT NULL,
   iduser  bigint(20) NOT NULL,
  UNIQUE KEY  UK_ax7nfmbmiqp3n17j0b6ibavp0  ( iduser ),
  KEY  FK_msy3yqjsrf1x684gkhnbisiwu  ( idDepartment ),
  CONSTRAINT  FK_ax7nfmbmiqp3n17j0b6ibavp0  FOREIGN KEY ( iduser ) REFERENCES  user  ( id ),
  CONSTRAINT  FK_msy3yqjsrf1x684gkhnbisiwu  FOREIGN KEY ( idDepartment ) REFERENCES  department  ( id )
);

--
-- Dumping data for table  department_to_user 
--

INSERT INTO  department_to_user  VALUES (10000,3);
INSERT INTO  department_to_user  VALUES (10000,4);

--
-- Table structure for table  documentfile 
--

CREATE TABLE  documentfile  (
   id  bigint(20) NOT NULL,
   contenType  varchar(255) NOT NULL,
   createDate  datetime NOT NULL,
   fileName  varchar(255) NOT NULL,
   documnetFileContent_id  int(11) NOT NULL,
   documentTypeId  bigint(20) NOT NULL,
  PRIMARY KEY ( id ),
  KEY  FK_f7key17h0cx278a32obgluswu  ( documnetFileContent_id ),
  KEY  FK_io6bye01rwbjfm4nova64tmpy  ( documentTypeId ),
  CONSTRAINT  FK_f7key17h0cx278a32obgluswu  FOREIGN KEY ( documnetFileContent_id ) REFERENCES  documentfilecontent  ( id ),
  CONSTRAINT  FK_io6bye01rwbjfm4nova64tmpy  FOREIGN KEY ( documentTypeId ) REFERENCES  documenttype  ( id )
);

--
-- Dumping data for table  documentfile 
--

--
-- Table structure for table  documentfile_appointment 
--

CREATE TABLE  documentfile_appointment  (
   id  bigint(20) NOT NULL,
   createDate  datetime NOT NULL,
   idAppointment  bigint(20) DEFAULT NULL,
   createUserId  bigint(20) DEFAULT NULL,
   idDocument  bigint(20) DEFAULT NULL,
  PRIMARY KEY ( id ),
  KEY  FK_8u44gswn5odopkxd5ejeqr9ay  ( idAppointment ),
  KEY  FK_nfaav8t8enq1is5x48hxtlpw  ( createUserId ),
  KEY  FK_laexvpu0446punt9tu0lv04wf  ( idDocument ),
  CONSTRAINT  FK_8u44gswn5odopkxd5ejeqr9ay  FOREIGN KEY ( idAppointment ) REFERENCES  appointment  ( id ),
  CONSTRAINT  FK_laexvpu0446punt9tu0lv04wf  FOREIGN KEY ( idDocument ) REFERENCES  documentfile  ( id ),
  CONSTRAINT  FK_nfaav8t8enq1is5x48hxtlpw  FOREIGN KEY ( createUserId ) REFERENCES  user  ( id )
);

--
-- Dumping data for table  documentfile_appointment 
--

--
-- Table structure for table  documentfilecontent 
--

CREATE TABLE  documentfilecontent  (
   id  int(11) NOT NULL,
   content  longblob,
  PRIMARY KEY ( id )
);

--
-- Dumping data for table  documentfilecontent 
--

--
-- Table structure for table  documenttype 
--

CREATE TABLE  documenttype  (
   id  bigint(20) NOT NULL,
   extensionType  varchar(255) NOT NULL,
   maxSize_kB  int(11) DEFAULT NULL,
   minSize_kB  int(11) DEFAULT NULL,
   typeName  varchar(255) NOT NULL,
  PRIMARY KEY ( id )
);

--
-- Dumping data for table  documenttype 
--

INSERT INTO  documenttype  VALUES (1,'PDF',10240,1,'LELET');
INSERT INTO  documenttype  VALUES (2,'WORD_DOC',10240,1,'LELET');
INSERT INTO  documenttype  VALUES (3,'PICTURE',10240,1,'LELET');
INSERT INTO  documenttype  VALUES (4,'PDF',10240,1,'ZAROJELENTES');
INSERT INTO  documenttype  VALUES (5,'WORD_DOC',10240,1,'ZAROJELENTES');
INSERT INTO  documenttype  VALUES (6,'PICTURE',10240,1,'ZAROJELENTES');
INSERT INTO  documenttype  VALUES (7,'PDF',10240,1,'RONTGEN_KEP');
INSERT INTO  documenttype  VALUES (8,'PICTURE',10240,1,'RONTGEN_KEP');
INSERT INTO  documenttype  VALUES (9,'ES3',61840,50,'RONTGEN_KEP');
INSERT INTO  documenttype  VALUES (10,'VIDEO',61840,50,'ULTRAHANG_VIDEO');
INSERT INTO  documenttype  VALUES (11,'ES3',61840,50,'ULTRAHANG_VIDEO');

--
-- Table structure for table  documenttype_to_consultationhourtype 
--

CREATE TABLE  documenttype_to_consultationhourtype  (
   id  bigint(20) NOT NULL,
   validFrom  datetime NOT NULL,
   validTo  datetime DEFAULT NULL,
   consultationHourTypeId  bigint(20) NOT NULL,
   documentTypeId  bigint(20) NOT NULL,
  PRIMARY KEY ( id ),
  KEY  FK_1egm0moyy3kd6axathm0hadj6  ( consultationHourTypeId ),
  KEY  FK_tluuc4sq2wn8um3c90d8svxj6  ( documentTypeId ),
  CONSTRAINT  FK_1egm0moyy3kd6axathm0hadj6  FOREIGN KEY ( consultationHourTypeId ) REFERENCES  consultationhour_type  ( id ),
  CONSTRAINT  FK_tluuc4sq2wn8um3c90d8svxj6  FOREIGN KEY ( documentTypeId ) REFERENCES  documenttype  ( id )
);

--
-- Dumping data for table  documenttype_to_consultationhourtype 
--

INSERT INTO  documenttype_to_consultationhourtype  VALUES (1,'2016-06-01 16:00:00',NULL,1,1);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (2,'2016-06-01 16:00:00',NULL,1,2);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (3,'2016-06-01 16:00:00',NULL,1,3);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (4,'2016-06-01 16:00:00',NULL,1,4);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (5,'2016-06-01 16:00:00',NULL,1,5);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (6,'2016-06-01 16:00:00',NULL,1,6);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (7,'2016-06-01 16:00:00',NULL,2,1);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (8,'2016-06-01 16:00:00',NULL,2,2);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (9,'2016-06-01 16:00:00',NULL,2,3);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (10,'2016-06-01 16:00:00',NULL,2,4);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (11,'2016-06-01 16:00:00',NULL,2,5);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (12,'2016-06-01 16:00:00',NULL,2,6);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (13,'2016-06-01 16:00:00',NULL,3,1);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (14,'2016-06-01 16:00:00',NULL,3,2);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (15,'2016-06-01 16:00:00',NULL,3,3);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (16,'2016-06-01 16:00:00',NULL,3,4);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (17,'2016-06-01 16:00:00',NULL,3,5);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (18,'2016-06-01 16:00:00',NULL,3,6);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (19,'2016-06-01 16:00:00',NULL,4,1);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (20,'2016-06-01 16:00:00',NULL,4,2);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (21,'2016-06-01 16:00:00',NULL,4,3);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (22,'2016-06-01 16:00:00',NULL,4,4);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (23,'2016-06-01 16:00:00',NULL,4,5);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (24,'2016-06-01 16:00:00',NULL,4,6);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (25,'2016-06-01 16:00:00',NULL,5,1);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (26,'2016-06-01 16:00:00',NULL,5,2);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (27,'2016-06-01 16:00:00',NULL,5,3);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (28,'2016-06-01 16:00:00',NULL,5,7);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (29,'2016-06-01 16:00:00',NULL,5,8);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (30,'2016-06-01 16:00:00',NULL,5,9);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (31,'2016-06-01 16:00:00',NULL,6,1);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (32,'2016-06-01 16:00:00',NULL,6,2);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (33,'2016-06-01 16:00:00',NULL,6,3);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (34,'2016-06-01 16:00:00',NULL,6,10);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (35,'2016-06-01 16:00:00',NULL,6,11);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (37,'2016-06-01 16:00:00',NULL,6,7);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (38,'2016-06-01 16:00:00',NULL,6,8);
INSERT INTO  documenttype_to_consultationhourtype  VALUES (39,'2016-06-01 16:00:00',NULL,6,9);

--
-- Table structure for table  personaldata 
--

CREATE TABLE  personaldata  (
   id  bigint(20) NOT NULL,
   birthDate  datetime NOT NULL,
   motherName  varchar(255) NOT NULL,
   firstName  varchar(255) NOT NULL,
   lastName  varchar(255) NOT NULL,
   title  varchar(20) DEFAULT NULL,
   phoneNumber  varchar(12) DEFAULT NULL,
   tajNumber  varchar(9) DEFAULT NULL,
   userId  bigint(20) DEFAULT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  UK_e88793fceior6jijuccfp5n95  ( tajNumber ),
  KEY  FK_xxx5hd9h9o8lb3q4g7rdj3da  ( userId ),
  CONSTRAINT  FK_xxx5hd9h9o8lb3q4g7rdj3da  FOREIGN KEY ( userId ) REFERENCES  user  ( id )
);

--
-- Dumping data for table  personaldata 
--

INSERT INTO  personaldata  VALUES (1,'1985-10-10 00:00:00','Kiss Piroska','Teszt','User1',NULL,'06301234567','123456789',1);
INSERT INTO  personaldata  VALUES (2,'1985-10-10 00:00:00','Valami Ember','Teszt','User2',NULL,'06301234567','123466789',2);
INSERT INTO  personaldata  VALUES (3,'1985-10-10 00:00:00','Teszt Admina','Admin','Béla','DR','06301234123','345556789',3);
INSERT INTO  personaldata  VALUES (4,'1985-10-10 00:00:00','Doktor Robertina','Doctor','József','DR','06301234123','345555289',4);

--
-- Table structure for table  role 
--

CREATE TABLE  role  (
   id  bigint(20) NOT NULL,
   code  varchar(255) NOT NULL,
   description  varchar(255) NOT NULL,
  PRIMARY KEY ( id )
);

--
-- Dumping data for table  role 
--

INSERT INTO  role  VALUES (1,'ROLE_USER','Egyszerű bejelentkezett felhasználói jog');
INSERT INTO  role  VALUES (2,'ROLE_MODIFY_PERSONAL_DATA','Személyes adatok módosítása');
INSERT INTO  role  VALUES (3,'ROLE_VIEW_CONSULTATION_HOUR','Rendelési idő megtekintése');
INSERT INTO  role  VALUES (4,'ROLE_VIEW_CONSULTATION_HOUR_APPOINTMENTS','Rendelési időhöz foglalt időpontok megtekintése');
INSERT INTO  role  VALUES (5,'ROLE_CREATE_CONSULTATION_HOUR','Rendelési idő készítése');
INSERT INTO  role  VALUES (6,'ROLE_MODIFY_CONSULTATION_HOUR','Rendelési idő szerkesztése');
INSERT INTO  role  VALUES (7,'ROLE_LIST_MY_APPOINTMENTS','Foglalt időpontjaim listázása');
INSERT INTO  role  VALUES (8,'ROLE_VIEW_APPOINTMENT','Foglalási időpont megtekintése');
INSERT INTO  role  VALUES (9,'ROLE_CREATE_APPOINTMENT','Foglalási időpont készítése');
INSERT INTO  role  VALUES (10,'ROLE_MODIFY_APPOINTMENT','Foglalási időpont módosítása');
INSERT INTO  role  VALUES (11,'ROLE_CANCEL_APPOINTMENT','Foglalási időpont visszamondása');
INSERT INTO  role  VALUES (12,'ROLE_VIEW_MY_DOCUMENTS','Saját leletek megtekintése');
INSERT INTO  role  VALUES (13,'ROLE_UPLOAD_DOCUMENT','Leletek feltöltése');
INSERT INTO  role  VALUES (14,'ROLE_DOWNLOAD_DOCUMENT','Leletek let?¶ltése');
INSERT INTO  role  VALUES (15,'ROLE_MODIFY_DEPARTMENT','Osztály adatainak módosítása');

--
-- Table structure for table  role_to_rolegroup 
--

CREATE TABLE  role_to_rolegroup  (
   idRole  bigint(20) NOT NULL,
   idRoleGroup  bigint(20) NOT NULL,
  KEY  FK_jxn1rdhhqs858ug811sctyoxo  ( idRoleGroup ),
  KEY  FK_6dmj4sif6onpcnrbovpb6brtg  ( idRole ),
  CONSTRAINT  FK_6dmj4sif6onpcnrbovpb6brtg  FOREIGN KEY ( idRole ) REFERENCES  role  ( id ),
  CONSTRAINT  FK_jxn1rdhhqs858ug811sctyoxo  FOREIGN KEY ( idRoleGroup ) REFERENCES  rolegroup  ( id )
);

--
-- Dumping data for table  role_to_rolegroup 
--

INSERT INTO  role_to_rolegroup  VALUES (1,1);
INSERT INTO  role_to_rolegroup  VALUES (3,1);
INSERT INTO  role_to_rolegroup  VALUES (7,1);
INSERT INTO  role_to_rolegroup  VALUES (8,1);
INSERT INTO  role_to_rolegroup  VALUES (9,1);
INSERT INTO  role_to_rolegroup  VALUES (10,1);
INSERT INTO  role_to_rolegroup  VALUES (11,1);
INSERT INTO  role_to_rolegroup  VALUES (12,1);
INSERT INTO  role_to_rolegroup  VALUES (13,1);
INSERT INTO  role_to_rolegroup  VALUES (14,1);
INSERT INTO  role_to_rolegroup  VALUES (2,1);
INSERT INTO  role_to_rolegroup  VALUES (1,2);
INSERT INTO  role_to_rolegroup  VALUES (8,2);
INSERT INTO  role_to_rolegroup  VALUES (3,2);
INSERT INTO  role_to_rolegroup  VALUES (4,2);
INSERT INTO  role_to_rolegroup  VALUES (5,2);
INSERT INTO  role_to_rolegroup  VALUES (6,2);
INSERT INTO  role_to_rolegroup  VALUES (13,2);
INSERT INTO  role_to_rolegroup  VALUES (14,2);
INSERT INTO  role_to_rolegroup  VALUES (15,2);
INSERT INTO  role_to_rolegroup  VALUES (1,3);
INSERT INTO  role_to_rolegroup  VALUES (3,3);
INSERT INTO  role_to_rolegroup  VALUES (4,3);
INSERT INTO  role_to_rolegroup  VALUES (5,3);
INSERT INTO  role_to_rolegroup  VALUES (6,3);
INSERT INTO  role_to_rolegroup  VALUES (2,3);
INSERT INTO  role_to_rolegroup  VALUES (15,3);

--
-- Table structure for table  rolegroup 
--

CREATE TABLE  rolegroup  (
   id  bigint(20) NOT NULL,
   code  varchar(255) DEFAULT NULL,
   description  varchar(255) DEFAULT NULL,
  PRIMARY KEY ( id )
);

--
-- Dumping data for table  rolegroup 
--

INSERT INTO  rolegroup  VALUES (1,'COMMON_USER','Egyszerű felhasznólói csoport');
INSERT INTO  rolegroup  VALUES (2,'DOCTOR','Orvos');
INSERT INTO  rolegroup  VALUES (3,'ADMIN','Adminstrator');

--
-- Table structure for table  user 
--

CREATE TABLE  user  (
   id  bigint(20) NOT NULL,
   email  varchar(255) NOT NULL,
   username  varchar(255) NOT NULL,
   enabled  tinyint(4) DEFAULT '1',
   password  varchar(255) DEFAULT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  UK_sb8bbouer5wak8vyiiy4pf2bx  ( username )
);


--
-- Dumping data for table  user 
--

INSERT INTO  user  VALUES (1,'a@a.a','beteg1',1,'$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre');
INSERT INTO  user  VALUES (2,'c@c.c','beteg2',1,'$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre');
INSERT INTO  user  VALUES (3,'b@b.b','orvos',1,'$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre');
INSERT INTO  user  VALUES (4,'d@d.d','orvos2',1,'$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre');

--
-- Table structure for table  user_to_rolegroup 
--

CREATE TABLE  user_to_rolegroup  (
   idRoleGroup  bigint(20) NOT NULL,
   idUser  bigint(20) NOT NULL,
  KEY  FK_l8sqkpfvuusr7mpxtw6ngu3hq  ( idUser ),
  KEY  FK_3f73tvuw1odxx8lmvfy6osfec  ( idRoleGroup ),
  CONSTRAINT  FK_3f73tvuw1odxx8lmvfy6osfec  FOREIGN KEY ( idRoleGroup ) REFERENCES  rolegroup  ( id ),
  CONSTRAINT  FK_l8sqkpfvuusr7mpxtw6ngu3hq  FOREIGN KEY ( idUser ) REFERENCES  user  ( id )
);

--
-- Dumping data for table  user_to_rolegroup 
--

INSERT INTO  user_to_rolegroup  VALUES (1,1);
INSERT INTO  user_to_rolegroup  VALUES (1,2);
INSERT INTO  user_to_rolegroup  VALUES (2,3);
INSERT INTO  user_to_rolegroup  VALUES (2,4);
