create database training;
use training;

CREATE TABLE `training`.`javaclass` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `training`.`pythonclass` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  PRIMARY KEY (`id`)) AUTO_INCREMENT=10;

CREATE TABLE `training`.`testclass` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45),
  `age` INT,
  PRIMARY KEY (`id`)) AUTO_INCREMENT =1000;


-- CRUD Create, Read, Update, Delete
SELECT * FROM training.javaclass;
INSERT INTO `training`.`javaclass` (`id`, `name`, `age`) VALUES ('6', 'Row', '20');
UPDATE `training`.`javaclass` SET `age` = '22' WHERE (`id` = '2');
DELETE FROM `training`.`javaclass` WHERE (`id` = '2');

-- stored procedure
DELIMITER //
CREATE procedure get_javaclass_by_id(IN user_id INT)
BEGIN
	SELECT * FROM javaclass where id = user_id;
END //
DELIMITER //

call training.get_javaclass_by_id(1);