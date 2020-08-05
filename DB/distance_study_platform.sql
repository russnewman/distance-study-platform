SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema distance_study_platform
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `distance_study_platform` ;

-- -----------------------------------------------------
-- Schema distance_study_platform
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `distance_study_platform` DEFAULT CHARACTER SET utf8 ;
USE `distance_study_platform` ;

-- -----------------------------------------------------
-- Table `distance_study_platform`.`FACULTIES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distance_study_platform`.`FACULTIES` ;

CREATE TABLE IF NOT EXISTS `distance_study_platform`.`FACULTIES` (
                                                                     `faculty_id` INT NOT NULL AUTO_INCREMENT,
                                                                     `faculty_name` VARCHAR(45) NULL,
                                                                     PRIMARY KEY (`faculty_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `distance_study_platform`.`GROUPS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distance_study_platform`.`GROUPS` ;

CREATE TABLE IF NOT EXISTS `distance_study_platform`.`GROUPS` (
                                                                  `group_id` INT NOT NULL AUTO_INCREMENT,
                                                                  `faculty_id` INT NOT NULL,
                                                                  PRIMARY KEY (`group_id`),
                                                                  INDEX `faculty_id_idx` (`faculty_id` ASC) VISIBLE,
                                                                  CONSTRAINT `faculty_id`
                                                                      FOREIGN KEY (`faculty_id`)
                                                                          REFERENCES `distance_study_platform`.`FACULTIES` (`faculty_id`)
                                                                          ON DELETE NO ACTION
                                                                          ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `distance_study_platform`.`USERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distance_study_platform`.`USERS` ;

CREATE TABLE IF NOT EXISTS `distance_study_platform`.`USERS` (
                                                                 `user_id` INT NOT NULL AUTO_INCREMENT,
                                                                 `email` VARCHAR(45) NOT NULL,
                                                                 `name` VARCHAR(45) NOT NULL,
                                                                 `surname` VARCHAR(45) NULL,
                                                                 `login` VARCHAR(45) NOT NULL,
                                                                 `password` VARCHAR(45) NOT NULL,
                                                                 PRIMARY KEY (`user_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `distance_study_platform`.`STUDENTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distance_study_platform`.`STUDENTS` ;

CREATE TABLE IF NOT EXISTS `distance_study_platform`.`STUDENTS` (
                                                                    `group_id` INT NOT NULL,
                                                                    `user_id` INT NOT NULL,
                                                                    PRIMARY KEY (`user_id`),
                                                                    INDEX `group_id_idx` (`group_id` ASC) VISIBLE,
                                                                    INDEX `user_id_students_idx` (`user_id` ASC) VISIBLE,
                                                                    UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
                                                                    CONSTRAINT `group_id_students`
                                                                        FOREIGN KEY (`group_id`)
                                                                            REFERENCES `distance_study_platform`.`GROUPS` (`group_id`)
                                                                            ON DELETE NO ACTION
                                                                            ON UPDATE NO ACTION,
                                                                    CONSTRAINT `user_id_students`
                                                                        FOREIGN KEY (`user_id`)
                                                                            REFERENCES `distance_study_platform`.`USERS` (`user_id`)
                                                                            ON DELETE NO ACTION
                                                                            ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `distance_study_platform`.`TEACHERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distance_study_platform`.`TEACHERS` ;

CREATE TABLE IF NOT EXISTS `distance_study_platform`.`TEACHERS` (
                                                                    `user_id` INT NOT NULL,
                                                                    INDEX `user_id_teachers_idx` (`user_id` ASC) VISIBLE,
                                                                    UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
                                                                    PRIMARY KEY (`user_id`),
                                                                    CONSTRAINT `user_id_teachers`
                                                                        FOREIGN KEY (`user_id`)
                                                                            REFERENCES `distance_study_platform`.`USERS` (`user_id`)
                                                                            ON DELETE NO ACTION
                                                                            ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `distance_study_platform`.`SUBJECTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distance_study_platform`.`SUBJECTS` ;

CREATE TABLE IF NOT EXISTS `distance_study_platform`.`SUBJECTS` (
                                                                    `subjects_id` INT NOT NULL AUTO_INCREMENT,
                                                                    `name` VARCHAR(45) NOT NULL,
                                                                    `description` VARCHAR(45) NULL,
                                                                    PRIMARY KEY (`subjects_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `distance_study_platform`.`CLASS_TIME`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distance_study_platform`.`CLASS_TIME` ;

CREATE TABLE IF NOT EXISTS `distance_study_platform`.`CLASS_TIME` (
                                                                      `class_time_id` INT NOT NULL,
                                                                      `class_time` TIME NOT NULL,
                                                                      `duration` TIME NOT NULL,
                                                                      PRIMARY KEY (`class_time_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `distance_study_platform`.`SCHEDULE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `distance_study_platform`.`SCHEDULE` ;

CREATE TABLE IF NOT EXISTS `distance_study_platform`.`SCHEDULE` (
                                                                    `schedule_id` INT NOT NULL,
                                                                    `subject_id` INT NOT NULL,
                                                                    `odd_week` TINYINT NOT NULL,
                                                                    `group_id` INT NOT NULL AUTO_INCREMENT,
                                                                    `class_time_id` INT NOT NULL,
                                                                    `class_type` VARCHAR(45) NOT NULL,
                                                                    `day_name` VARCHAR(45) NOT NULL,
                                                                    `user_id` INT NOT NULL,
                                                                    PRIMARY KEY (`schedule_id`),
                                                                    INDEX `group_id_idx` (`group_id` ASC) VISIBLE,
                                                                    INDEX `class_time_id_idx` (`class_time_id` ASC) VISIBLE,
                                                                    INDEX `subject_id_idx` (`subject_id` ASC) VISIBLE,
                                                                    INDEX `fk_SCHEDULE_TEACHERS1_idx` (`user_id` ASC) VISIBLE,
                                                                    CONSTRAINT `group_id_schedule`
                                                                        FOREIGN KEY (`group_id`)
                                                                            REFERENCES `distance_study_platform`.`GROUPS` (`group_id`)
                                                                            ON DELETE NO ACTION
                                                                            ON UPDATE NO ACTION,
                                                                    CONSTRAINT `class_time_id_schedule`
                                                                        FOREIGN KEY (`class_time_id`)
                                                                            REFERENCES `distance_study_platform`.`CLASS_TIME` (`class_time_id`)
                                                                            ON DELETE NO ACTION
                                                                            ON UPDATE NO ACTION,
                                                                    CONSTRAINT `subject_id_schedule`
                                                                        FOREIGN KEY (`subject_id`)
                                                                            REFERENCES `distance_study_platform`.`SUBJECTS` (`subjects_id`)
                                                                            ON DELETE NO ACTION
                                                                            ON UPDATE NO ACTION,
                                                                    CONSTRAINT `fk_SCHEDULE_TEACHERS1`
                                                                        FOREIGN KEY (`user_id`)
                                                                            REFERENCES `distance_study_platform`.`TEACHERS` (`user_id`)
                                                                            ON DELETE NO ACTION
                                                                            ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
