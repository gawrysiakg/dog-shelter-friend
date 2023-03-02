insert into volunteer (id, email, first_name, last_name, volunteer.name, volunteer.password, phone)  values (id+1, 'admin@cd.ef', 'Admin Name', 'Admin Surname', 'ADMIN','ADMIN', 500600700);
insert into volunteer (id, email, first_name, last_name, volunteer.name, volunteer.password, phone)  values (id+2,'user@cd.ef', 'User Name', 'User Surname', 'USER','USER', 700600700);
insert into volunteer (id, email, first_name, last_name, volunteer.name, volunteer.password, phone)  values (id+3,'user2@cd.ef', 'User2 Name', 'User2 Surname', 'USER','USER', 998875550);


insert into dogs (id, dog_bread, in_shelter, dogs.name) values (id+1,'Mixed', true, 'Brutus') ;
insert into dogs (id, dog_bread, in_shelter, dogs.name) values (id+2,'Labrador', true, 'Remo');
insert into dogs (id, dog_bread, in_shelter, dogs.name) values (id+3,'Terrier', false, 'Lili');
insert into dogs (id, dog_bread, in_shelter, dogs.name) values (id+4,'Mixed', true, 'Old Bob');
insert into dogs (id, dog_bread, in_shelter, dogs.name) values (id+5,'Mixed', false, 'Olo');


COMMIT;


