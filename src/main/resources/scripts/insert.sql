 --
-- Table structure for table user
--
ALTER TABLE users ADD password VARCHAR(255);
--
-- Dumping data for table user
--

INSERT INTO users(id, username, email, password) VALUES (1,'beteg1','a@a.a','$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre');
INSERT INTO users(id, username, email, password) VALUES (2,'beteg2','c@c.c','$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre');
INSERT INTO users(id, username, email, password) VALUES (3,'orvos' ,'b@b.b','$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre');
INSERT INTO users(id, username, email, password) VALUES (4,'orvos2','d@d.d','$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre');


--
-- Table structure for table personaldata
--

--
-- Dumping data for table personaldata
--

INSERT INTO personaldata VALUES (1,'1985-10-10 00:00:00','Kiss Piroska','Teszt','User1',NULL,'06301234567','123456789',1);
INSERT INTO personaldata VALUES (2,'1985-10-10 00:00:00','Valami Ember','Teszt','User2',NULL,'06301234567','123466789',2);
INSERT INTO personaldata VALUES (3,'1985-10-10 00:00:00','Teszt Admina','Admin','Béla','DR','06301234123','345556789',3);
INSERT INTO personaldata VALUES (4,'1985-10-10 00:00:00','Doktor Robertina','Doctor','József','DR','06301234123','345555289',4);


--
-- Table structure for table department
--

--
-- Dumping data for table department
--

INSERT INTO department VALUES (10000,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','Szemészet','36305248967','IB025',3);
INSERT INTO department VALUES (10001,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','Ortopédia','36305345967','IB025',4);


--
-- Table structure for table department_to_user
--

--
-- Dumping data for table department_to_user
--

INSERT INTO department_to_user VALUES (10000,3);
INSERT INTO department_to_user VALUES (10000,4);


--
-- Table structure for table consultationhour_type
--

--
-- Dumping data for table consultationhour_type
--

INSERT INTO consultationhour_type VALUES (2,'Látás vizsgélat',10000);
INSERT INTO consultationhour_type VALUES (3,'Szürkehályog vizsg?álat',10000);
INSERT INTO consultationhour_type VALUES (1,'Szem mûtét',10000);
INSERT INTO consultationhour_type VALUES (4,'Általános vizsgálat',10001);
INSERT INTO consultationhour_type VALUES (5,'Lúdtalp vizsgálat',10001);
INSERT INTO consultationhour_type VALUES (6,'Protézis mûtét',10001);


--
-- Table structure for table documenttype
--

--
-- Dumping data for table documenttype
--
/*
INSERT INTO documenttype VALUES (1,'PDF',10240,1,'LELET');
INSERT INTO documenttype VALUES (2,'WORD_DOC',10240,1,'LELET');
INSERT INTO documenttype VALUES (3,'PICTURE',10240,1,'LELET');
INSERT INTO documenttype VALUES (4,'PDF',10240,1,'ZAROJELENTES');
INSERT INTO documenttype VALUES (5,'WORD_DOC',10240,1,'ZAROJELENTES');
INSERT INTO documenttype VALUES (6,'PICTURE',10240,1,'ZAROJELENTES');
INSERT INTO documenttype VALUES (7,'PDF',10240,1,'RONTGEN_KEP');
INSERT INTO documenttype VALUES (8,'PICTURE',10240,1,'RONTGEN_KEP');
INSERT INTO documenttype VALUES (9,'ES3',61840,50,'RONTGEN_KEP');
INSERT INTO documenttype VALUES (10,'VIDEO',61840,50,'ULTRAHANG_VIDEO');
INSERT INTO documenttype VALUES (11,'ES3',61840,50,'ULTRAHANG_VIDEO');

*/
--
-- Table structure for table documenttype_to_consultationhourtype
--

--
-- Dumping data for table documenttype_to_consultationhourtype
--
/*
INSERT INTO documenttype_to_consultationhourtype VALUES (1,'2016-06-01 16:00:00',NULL,1,1);
INSERT INTO documenttype_to_consultationhourtype VALUES (2,'2016-06-01 16:00:00',NULL,1,2);
INSERT INTO documenttype_to_consultationhourtype VALUES (3,'2016-06-01 16:00:00',NULL,1,3);
INSERT INTO documenttype_to_consultationhourtype VALUES (4,'2016-06-01 16:00:00',NULL,1,4);
INSERT INTO documenttype_to_consultationhourtype VALUES (5,'2016-06-01 16:00:00',NULL,1,5);
INSERT INTO documenttype_to_consultationhourtype VALUES (6,'2016-06-01 16:00:00',NULL,1,6);
INSERT INTO documenttype_to_consultationhourtype VALUES (7,'2016-06-01 16:00:00',NULL,2,1);
INSERT INTO documenttype_to_consultationhourtype VALUES (8,'2016-06-01 16:00:00',NULL,2,2);
INSERT INTO documenttype_to_consultationhourtype VALUES (9,'2016-06-01 16:00:00',NULL,2,3);
INSERT INTO documenttype_to_consultationhourtype VALUES (10,'2016-06-01 16:00:00',NULL,2,4);
INSERT INTO documenttype_to_consultationhourtype VALUES (11,'2016-06-01 16:00:00',NULL,2,5);
INSERT INTO documenttype_to_consultationhourtype VALUES (12,'2016-06-01 16:00:00',NULL,2,6);
INSERT INTO documenttype_to_consultationhourtype VALUES (13,'2016-06-01 16:00:00',NULL,3,1);
INSERT INTO documenttype_to_consultationhourtype VALUES (14,'2016-06-01 16:00:00',NULL,3,2);
INSERT INTO documenttype_to_consultationhourtype VALUES (15,'2016-06-01 16:00:00',NULL,3,3);
INSERT INTO documenttype_to_consultationhourtype VALUES (16,'2016-06-01 16:00:00',NULL,3,4);
INSERT INTO documenttype_to_consultationhourtype VALUES (17,'2016-06-01 16:00:00',NULL,3,5);
INSERT INTO documenttype_to_consultationhourtype VALUES (18,'2016-06-01 16:00:00',NULL,3,6);
INSERT INTO documenttype_to_consultationhourtype VALUES (19,'2016-06-01 16:00:00',NULL,4,1);
INSERT INTO documenttype_to_consultationhourtype VALUES (20,'2016-06-01 16:00:00',NULL,4,2);
INSERT INTO documenttype_to_consultationhourtype VALUES (21,'2016-06-01 16:00:00',NULL,4,3);
INSERT INTO documenttype_to_consultationhourtype VALUES (22,'2016-06-01 16:00:00',NULL,4,4);
INSERT INTO documenttype_to_consultationhourtype VALUES (23,'2016-06-01 16:00:00',NULL,4,5);
INSERT INTO documenttype_to_consultationhourtype VALUES (24,'2016-06-01 16:00:00',NULL,4,6);
INSERT INTO documenttype_to_consultationhourtype VALUES (25,'2016-06-01 16:00:00',NULL,5,1);
INSERT INTO documenttype_to_consultationhourtype VALUES (26,'2016-06-01 16:00:00',NULL,5,2);
INSERT INTO documenttype_to_consultationhourtype VALUES (27,'2016-06-01 16:00:00',NULL,5,3);
INSERT INTO documenttype_to_consultationhourtype VALUES (28,'2016-06-01 16:00:00',NULL,5,7);
INSERT INTO documenttype_to_consultationhourtype VALUES (29,'2016-06-01 16:00:00',NULL,5,8);
INSERT INTO documenttype_to_consultationhourtype VALUES (30,'2016-06-01 16:00:00',NULL,5,9);
INSERT INTO documenttype_to_consultationhourtype VALUES (31,'2016-06-01 16:00:00',NULL,6,1);
INSERT INTO documenttype_to_consultationhourtype VALUES (32,'2016-06-01 16:00:00',NULL,6,2);
INSERT INTO documenttype_to_consultationhourtype VALUES (33,'2016-06-01 16:00:00',NULL,6,3);
INSERT INTO documenttype_to_consultationhourtype VALUES (34,'2016-06-01 16:00:00',NULL,6,10);
INSERT INTO documenttype_to_consultationhourtype VALUES (35,'2016-06-01 16:00:00',NULL,6,11);
INSERT INTO documenttype_to_consultationhourtype VALUES (37,'2016-06-01 16:00:00',NULL,6,7);
INSERT INTO documenttype_to_consultationhourtype VALUES (38,'2016-06-01 16:00:00',NULL,6,8);
INSERT INTO documenttype_to_consultationhourtype VALUES (39,'2016-06-01 16:00:00',NULL,6,9);
*/
--
-- Table structure for table consultationhour
--

--
-- Dumping data for table consultationhour
--

INSERT INTO consultationhour VALUES (10000,'2017-06-01 16:00:00','2017-06-01 16:00:00',7,'RD41',10000,3,1);
INSERT INTO consultationhour VALUES (10001,'2017-05-10 16:00:00','2017-05-10 16:00:00',7,'RD41',10000,4,4);
INSERT INTO consultationhour VALUES (10002,'2015-06-01 16:00:00','2015-06-01 16:00:00',7,'RD41',10000,3,1);


--
-- Table structure for table appointment
--

--
-- Dumping data for table appointment
--

INSERT INTO appointment VALUES (1,'Fáj a szemem',10000,1);


--
-- Table structure for table role
--

--
-- Dumping data for table role
--

INSERT INTO role VALUES (1,'ROLE_USER','Egyszerû bejelentkezett felhasználói jog');
INSERT INTO role VALUES (2,'ROLE_MODIFY_PERSONAL_DATA','Személyes adatok módosítása');
INSERT INTO role VALUES (3,'ROLE_VIEW_CONSULTATION_HOUR','Rendelési idõ megtekintése');
INSERT INTO role VALUES (4,'ROLE_VIEW_CONSULTATION_HOUR_APPOINTMENTS','Rendelési idõhöz foglalt idõpontok megtekintése');
INSERT INTO role VALUES (5,'ROLE_CREATE_CONSULTATION_HOUR','Rendelési idõ készítése');
INSERT INTO role VALUES (6,'ROLE_MODIFY_CONSULTATION_HOUR','Rendelési idõ szerkesztése');
INSERT INTO role VALUES (7,'ROLE_LIST_MY_APPOINTMENTS','Foglalt idõpontjaim listázása');
INSERT INTO role VALUES (8,'ROLE_VIEW_APPOINTMENT','Foglalási idõpont megtekintése');
INSERT INTO role VALUES (9,'ROLE_CREATE_APPOINTMENT','Foglalási idõpont készítése');
INSERT INTO role VALUES (10,'ROLE_MODIFY_APPOINTMENT','Foglalási idõpont módosítása');
INSERT INTO role VALUES (11,'ROLE_CANCEL_APPOINTMENT','Foglalási idõpont visszamondása');
INSERT INTO role VALUES (12,'ROLE_VIEW_MY_DOCUMENTS','Saját leletek megtekintése');
INSERT INTO role VALUES (13,'ROLE_UPLOAD_DOCUMENT','Leletek feltöltése');
INSERT INTO role VALUES (14,'ROLE_DOWNLOAD_DOCUMENT','Leletek let?¶ltése');
INSERT INTO role VALUES (15,'ROLE_MODIFY_DEPARTMENT','Osztály adatainak módosítása');


--
-- Table structure for table rolegroup
--

--
-- Dumping data for table rolegroup
--

INSERT INTO rolegroup VALUES (1,'COMMON_USER','Egyszerû felhasznólói csoport');
INSERT INTO rolegroup VALUES (2,'DOCTOR','Orvos');
INSERT INTO rolegroup VALUES (3,'ADMIN','Adminstrator');

--
-- Table structure for table role_to_rolegroup
--

--
-- Dumping data for table role_to_rolegroup
--

INSERT INTO role_to_rolegroup VALUES (1,1);
INSERT INTO role_to_rolegroup VALUES (3,1);
INSERT INTO role_to_rolegroup VALUES (7,1);
INSERT INTO role_to_rolegroup VALUES (8,1);
INSERT INTO role_to_rolegroup VALUES (9,1);
INSERT INTO role_to_rolegroup VALUES (10,1);
INSERT INTO role_to_rolegroup VALUES (11,1);
INSERT INTO role_to_rolegroup VALUES (12,1);
INSERT INTO role_to_rolegroup VALUES (13,1);
INSERT INTO role_to_rolegroup VALUES (14,1);
INSERT INTO role_to_rolegroup VALUES (2,1);
INSERT INTO role_to_rolegroup VALUES (1,2);
INSERT INTO role_to_rolegroup VALUES (8,2);
INSERT INTO role_to_rolegroup VALUES (3,2);
INSERT INTO role_to_rolegroup VALUES (4,2);
INSERT INTO role_to_rolegroup VALUES (5,2);
INSERT INTO role_to_rolegroup VALUES (6,2);
INSERT INTO role_to_rolegroup VALUES (13,2);
INSERT INTO role_to_rolegroup VALUES (14,2);
INSERT INTO role_to_rolegroup VALUES (15,2);
INSERT INTO role_to_rolegroup VALUES (1,3);
INSERT INTO role_to_rolegroup VALUES (3,3);
INSERT INTO role_to_rolegroup VALUES (4,3);
INSERT INTO role_to_rolegroup VALUES (5,3);
INSERT INTO role_to_rolegroup VALUES (6,3);
INSERT INTO role_to_rolegroup VALUES (2,3);
INSERT INTO role_to_rolegroup VALUES (15,3);


--
-- Table structure for table user_to_rolegroup
--

--
-- Dumping data for table user_to_rolegroup
--

INSERT INTO user_to_rolegroup VALUES (1,1);
INSERT INTO user_to_rolegroup VALUES (1,2);
INSERT INTO user_to_rolegroup VALUES (2,3);
INSERT INTO user_to_rolegroup VALUES (2,4);

