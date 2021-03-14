create table users.usertable;
select * from users.usertable;
insert into users.usertable (useusertabler_id,relationWith,relationName,marriageStatus,partner,name,type,description,address,phone,current_contact_person) 
values (1,2,null,"Single",3,"harish",null,"new user","test","8142210004",null) ;
CREATE TABLE IF NOT EXISTS users.`userTable` (
  `user_id` INT NOT NULL,
  `relationWith` INT NOT NULL,
  `relationName` VARCHAR(45) NULL,
  `marriageStatus` VARCHAR(45) NULL,
  `partner` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `current_contact_person` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;

Select st.StudentID, Ex.Result from student st, Exam as Ex where st.studentID = Ex. StudentID;
