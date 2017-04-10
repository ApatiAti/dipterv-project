
 delete from documentfile;
 delete from documentfile_appointment;

 delete from appointment;
 delete from consultationhour_tpye;
 delete from consultationhour;
 delete from department;
 delete from department_to_user;
 
 delete from user_to_rolegroup;
 delete from personalData;
 delete from user;

 delete from role_to_rolegroup;
 delete from rolegroup;
 delete from role;
  


-- == [Tábla módosítások]== --
-- ALTER TABLE user ADD COLUMN enabled TINYINT(4) NULL DEFAULT '1';
-- ALTER TABLE user ADD COLUMN password VARCHAR(255) NULL;

-- == [Lakosok és vezetők beállítása]== --
SET @patient1 = 'beteg1';
SET @patient2 = 'beteg2';
SET @doctor1 = 'orvos';
SET @doctor2 = 'orvos2';
SET @departmentId1 = 10000;
SET @departmentId2 = 10001;
SET @consultationHourId1 = 10000;
SET @consultationHourId2 = 10001;

SET @consultationHour_type_name1 ='Szem műtét';
SET @consultationHour_type_name2 ='Látás vizsgálat';
SET @consultationHour_type_name3 ='Szürkehályog vizsgálat';
                                
SET @consultationHour_type_name4 ='Általános vizsgálat';
SET @consultationHour_type_name5 ='Lúdtalp vizsgálat';
SET @consultationHour_type_name6 ='Protézis műtét';

-- jelszó = admindocumentfile_appointmentdepartment_to_userdepartment_to_userdocumentfile_appointment
insert into user (email, username, password, position) values('a@a.a', @patient1, '$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre', 'PATIENT');
insert into user (email, username, password, position) values('c@c.c', @patient2, '$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre', 'PATIENT');
insert into user (email, username, password, position) values('b@b.b', @doctor1, '$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre', 'DOCTOR');
insert into user (email, username, password, position) values('d@d.d', @doctor2, '$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre', 'DOCTOR');
-- insert into citizen (email, password, experiencePoints, fatigue, username, money, lastRelaxTime, currentRegion_id) values('d@d.d', '$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre',1,100, 'Teszt user1_1', 0  ,'2015-12-13 01:02:03' , (select id from region where name='region1'));
-- insert into citizen (email, password, experiencePoints, fatigue, username, money, lastRelaxTime, currentRegion_id) values('c@c.c', '$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre',1,100, 'Teszt user3', 400, '2015-12-13 01:02:03' , (select id from region where name='region3'));
-- insert into citizen (email, password, experiencePoints, fatigue, username, money, lastRelaxTime, currentRegion_id) values('e@e.e', '$2a$10$03s4NFWv7Yz70.vKFMUsru.k3ARYzBxNxm/SeW.ZsBGJUAEGCbgre',1,100, 'admin', 0 ,'2015-12-13 01:02:03' , (select id from region where name='region3'));
     
insert into personalData(birthDate, motherName, firstName, lastName, title, phoneNumber, tajNumber, userId) values(STR_TO_DATE('1985-10-10', '%Y-%m-%d') , 'Kiss Piroska', 'Teszt', 'User1', null, '06301234567', '123456789', (select id from user where username = @patient1));
insert into personalData(birthDate, motherName, firstName, lastName, title, phoneNumber, tajNumber, userId) values(STR_TO_DATE('1985-10-10', '%Y-%m-%d') , 'Valami Ember', 'Teszt', 'User2', null, '06301234567', '123466789', (select id from user where username = @patient2));
insert into personalData(birthDate, motherName, firstName, lastName, title, phoneNumber, tajNumber, userId) values(STR_TO_DATE('1985-10-10', '%Y-%m-%d') , 'Teszt Admina', 'Admin', 'Béla', 'DR', '06301234123', '345556789', (select id from user where username = @doctor1));
insert into personalData(birthDate, motherName, firstName, lastName, title, phoneNumber, tajNumber, userId) values(STR_TO_DATE('1985-10-10', '%Y-%m-%d') , 'Doktor Róbertina', 'Doctor', 'József', 'DR', '06301234123', '345556789', (select id from user where username = @doctor2));


-- ==[ Korházi osztályok beállítása ]==--

insert into department(id, name, phoneNumber, place, departmentHead, description) values ( @departmentId1 ,'Szemészet', '36305248967', 'IB025', (select id from user where username = @doctor1), 'óóóóLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');
insert into department(id, name, phoneNumber, place, departmentHead, description) values ( @departmentId2 ,'Ortopédia', '36305345967', 'IB025', (select id from user where username = @doctor2), 'óóóóLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');
                         
insert into department_to_user(idDepartment, idUser) values (@departmentId1, (select id from user where username = @doctor1);
insert into department_to_user(idDepartment, idUser) values (@departmentId1, (select id from user where username = @doctor2);


-- ==[ Fogadó óra típusok ]==--
insert into consultationhour_type(name, departmentId) values( @consultationHour_type_name1, @departmentId1);
insert into consultationhour_type(name, departmentId) values( @consultationHour_type_name2, @departmentId1);
insert into consultationhour_type(name, departmentId) values( @consultationHour_type_name3, @departmentId1);

insert into consultationhour_type(name, departmentId) values( @consultationHour_type_name4, @departmentId2);
insert into consultationhour_type(name, departmentId) values( @consultationHour_type_name5, @departmentId2);
insert into consultationhour_type(name, departmentId) values( @consultationHour_type_name6, @departmentId2);


-- ==[ Fogadó órák insertje ]==--
insert into consultationhour(id, beginDate, endDate, maxNumberOfPatient, room, departmentId, consultationhour_tpyeid) values ( @consultationHourId1, STR_TO_DATE('2017-06-01 16:00:00', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2017-06-01 16:00:00', '%Y-%m-%d %H:%i:%s'), 7, 'RD41', @departmentId1, (select id from consultationhour_type where name = @consultationHour_type_name1 and departmentId = @departmentId1));
insert into consultationhour(id, beginDate, endDate, maxNumberOfPatient, room, departmentId, consultationhour_tpyeid) values ( @consultationHourId2, STR_TO_DATE('2017-05-10 16:00:00', '%Y-%m-%d %H:%i:%s'), STR_TO_DATE('2017-05-10 16:00:00', '%Y-%m-%d %H:%i:%s'), 7, 'RD41', @departmentId1, (select id from consultationhour_type where name = @consultationHour_type_name4 and departmentId = @departmentId2));

-- ==[ Időpont foglalások]
insert into appointment(complaints, consultationHourId, patientId) values ('Fáj a szemem', @consultationHourId1, (select id from user where username = @patient1));

-- ==[ jogosultságok és szerepkörök létrehozása]== --
insert into role (code, description) values ('ROLE_USER', 'Egyszerű bejelentkezett felhasználói jog');
insert into role (code, description) values ('ROLE_MODIFY_PERSONAL_DATA',  'Személyes adatok módosításas');

insert into role (code, description) values ('ROLE_VIEW_CONSULTATION_HOUR', 'Rendelési idő megtekintése');
insert into role (code, description) values ('ROLE_VIEW_CONSULTATION_HOUR_APPOINTMENTS', 'Rendelési időhöz foglalt időpontok megtekintése');
insert into role (code, description) values ('ROLE_CREATE_CONSULTATION_HOUR', 'Rendelési idő készítése');
insert into role (code, description) values ('ROLE_MODIFY_CONSULTATION_HOUR', 'Rendelési idő szerkesztése');

insert into role (code, description) values ('ROLE_LIST_MY_APPOINTMENTS', 'Foglalt időpontjaim listázása');
insert into role (code, description) values ('ROLE_VIEW_APPOINTMENT', 'Foglalási időpont megtekintése');
insert into role (code, description) values ('ROLE_CREATE_APPOINTMENT', 'Foglalási időpont készítése');
insert into role (code, description) values ('ROLE_MODIFY_APPOINTMENT', 'Foglalási időpont módosítása');
insert into role (code, description) values ('ROLE_CANCEL_APPOINTMENT', 'Foglalási időpont visszamondása');

insert into role (code, description) values ('ROLE_VIEW_MY_DOCUMENTS',  'Saját leletek megtekintése');
insert into role (code, description) values ('ROLE_UPLOAD_DOCUMENT', 'Leletek feltöltése');
insert into role (code, description) values ('ROLE_DOWNLOAD_DOCUMENT',  'Leletek letöltése');

insert into role (code, description) values ('ROLE_MODIFY_DEPARTMENT',  'Osztály adatainak módosítása');

     
-- ==[ Jogosultság csoportok látrehozása]== --
insert into rolegroup (code, description) values ('COMMON_USER', 'Egyszerű felhasználói csoport');
insert into rolegroup (code, description) values ('DOCTOR', 'Orvos');
insert into rolegroup (code, description) values ('ADMIN', 'Adminstrator');


-- ==[Beteg jogosultágainak létrehozása ]==--
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'COMMON_USER'), (select id from role where code = 'ROLE_USER'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'COMMON_USER'), (select id from role where code = 'ROLE_VIEW_CONSULTATION_HOUR'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'COMMON_USER'), (select id from role where code = 'ROLE_LIST_MY_APPOINTMENTS'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'COMMON_USER'), (select id from role where code = 'ROLE_VIEW_APPOINTMENT'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'COMMON_USER'), (select id from role where code = 'ROLE_CREATE_APPOINTMENT'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'COMMON_USER'), (select id from role where code = 'ROLE_MODIFY_APPOINTMENT'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'COMMON_USER'), (select id from role where code = 'ROLE_CANCEL_APPOINTMENT'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'COMMON_USER'), (select id from role where code = 'ROLE_VIEW_MY_DOCUMENTS'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'COMMON_USER'), (select id from role where code = 'ROLE_UPLOAD_DOCUMENT'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'COMMON_USER'), (select id from role where code = 'ROLE_DOWNLOAD_DOCUMENT'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'COMMON_USER'), (select id from role where code = 'ROLE_MODIFY_PERSONAL_DATA'));

-- ==[Orvos jogosultágainak létrehozása ]==--
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'DOCTOR'), (select id from role where code = 'ROLE_USER'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'DOCTOR'), (select id from role where code = 'ROLE_VIEW_CONSULTATION_HOUR'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'DOCTOR'), (select id from role where code = 'ROLE_VIEW_CONSULTATION_HOUR_APPOINTMENTS'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'DOCTOR'), (select id from role where code = 'ROLE_CREATE_CONSULTATION_HOUR'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'DOCTOR'), (select id from role where code = 'ROLE_MODIFY_CONSULTATION_HOUR'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'DOCTOR'), (select id from role where code = 'ROLE_UPLOAD_DOCUMENT'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'DOCTOR'), (select id from role where code = 'ROLE_DOWNLOAD_DOCUMENT'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'DOCTOR'), (select id from role where code = 'ROLE_MODIFY_DEPARTMENT'));


-- ==[adminisztrátor jogosultágainak létrehozása ]==--
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'ADMIN'), (select id from role where code = 'ROLE_USER'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'ADMIN'), (select id from role where code = 'ROLE_VIEW_CONSULTATION_HOUR'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'ADMIN'), (select id from role where code = 'ROLE_VIEW_CONSULTATION_HOUR_APPOINTMENTS'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'ADMIN'), (select id from role where code = 'ROLE_CREATE_CONSULTATION_HOUR'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'ADMIN'), (select id from role where code = 'ROLE_MODIFY_CONSULTATION_HOUR'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'ADMIN'), (select id from role where code = 'ROLE_MODIFY_PERSONAL_DATA'));
insert into role_to_rolegroup (idRoleGroup, idRole) values((select id from rolegroup where code = 'ADMIN'), (select id from role where code = 'ROLE_MODIFY_DEPARTMENT'));

-- ==[ szerepkör beállítása 'Teszt user1' citizenhez ]==--
insert into user_to_rolegroup (iduser, idRoleGroup)  value ( (select id from user where username = @patient1), (select id from rolegroup where code = 'COMMON_USER'));
insert into user_to_rolegroup (iduser, idRoleGroup)  value ( (select id from user where username = @patient2), (select id from rolegroup where code = 'COMMON_USER'));
insert into user_to_rolegroup (iduser, idRoleGroup)  value ( (select id from user where username = @doctor1), (select id from rolegroup where code = 'DOCTOR'));
insert into user_to_rolegroup (iduser, idRoleGroup)  value ( (select id from user where username = @doctor2), (select id from rolegroup where code = 'DOCTOR'));
