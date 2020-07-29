

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;


CREATE TABLE IF NOT EXISTS `mydb`.`FACULTIES` (
  `faculty_id` INT NOT NULL AUTO_INCREMENT,
  `faculty_name` VARCHAR(45) NULL,
  PRIMARY KEY (`faculty_id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `mydb`.`GROUPS` (
  `group_id` INT NOT NULL AUTO_INCREMENT,
  `faculty_id` INT NOT NULL,
  PRIMARY KEY (`group_id`),
  INDEX `faculty_id_idx` (`faculty_id` ASC),
  CONSTRAINT `faculty_id`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `mydb`.`FACULTIES` (`faculty_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `mydb`.`USERS` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `mydb`.`STUDENTS` (
  `student_id` INT NOT NULL AUTO_INCREMENT,
  `group_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`student_id`),
  INDEX `group_id_idx` (`group_id` ASC),
  INDEX `user_id_students_idx` (`user_id` ASC) ,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  CONSTRAINT `group_id_students`
    FOREIGN KEY (`group_id`)
    REFERENCES `mydb`.`GROUPS` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_id_students`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`USERS` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `mydb`.`TEACHERS` (
  `teacher_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  PRIMARY KEY (`teacher_id`),
  UNIQUE INDEX `teacher_id_UNIQUE` (`teacher_id` ASC),
  INDEX `user_id_teachers_idx` (`user_id` ASC) ,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) ,
  CONSTRAINT `user_id_teachers`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`USERS` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `mydb`.`SUBJECTS` (
  `subjects_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`subjects_id`))
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `mydb`.`CLASS_TIME` (
  `class_time_id` INT NOT NULL AUTO_INCREMENT,
  `class_time` TIME NOT NULL,
  `duration` TIME NOT NULL,
  PRIMARY KEY (`class_time_id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `mydb`.`SCHEDULE` (
  `schedule_id` INT NOT NULL AUTO_INCREMENT,
  `subject_id` INT NOT NULL,
  `odd_week` TINYINT NOT NULL,
  `group_id` INT NOT NULL,
  `class_time_id` INT NOT NULL,
  `class_type` VARCHAR(45) NOT NULL,
  `day_name` VARCHAR(45) NOT NULL,
  `teacher_id` INT NULL,
  PRIMARY KEY (`schedule_id`),
  INDEX `group_id_idx` (`group_id` ASC),
  INDEX `class_time_id_idx` (`class_time_id` ASC),
  INDEX `subject_id_idx` (`subject_id` ASC),
  INDEX `teacher_id_schedule_idx` (`teacher_id` ASC),
  CONSTRAINT `group_id_schedule`
    FOREIGN KEY (`group_id`)
    REFERENCES `mydb`.`GROUPS` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `class_time_id_schedule`
    FOREIGN KEY (`class_time_id`)
    REFERENCES `mydb`.`CLASS_TIME` (`class_time_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `subject_id_schedule`
    FOREIGN KEY (`subject_id`)
    REFERENCES `mydb`.`SUBJECTS` (`subjects_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `teacher_id_schedule`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `mydb`.`TEACHERS` (`teacher_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;