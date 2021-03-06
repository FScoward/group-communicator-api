-- MySQL Script generated by MySQL Workbench
-- 06/18/16 21:22:47
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema group_communicator
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema group_communicator
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `group_communicator` DEFAULT CHARACTER SET utf8mb4 ;
USE `group_communicator` ;

-- -----------------------------------------------------
-- Table `group_communicator`.`GROUPS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group_communicator`.`GROUPS` (
  `GROUP_ID` BIGINT(20) NOT NULL COMMENT '',
  `GROUP_NAME` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`GROUP_ID`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `group_communicator`.`USERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group_communicator`.`USERS` (
  `USER_ID` BIGINT(20) NOT NULL COMMENT '',
  `USER_NAME` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`USER_ID`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `group_communicator`.`USERS_has_GROUPS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group_communicator`.`USERS_has_GROUPS` (
  `USERS_USER_ID` BIGINT(20) NOT NULL COMMENT '',
  `GROUPS_GROUP_ID` BIGINT(20) NOT NULL COMMENT '',
  PRIMARY KEY (`USERS_USER_ID`, `GROUPS_GROUP_ID`)  COMMENT '',
  INDEX `fk_USERS_has_GROUPS_GROUPS1_idx` (`GROUPS_GROUP_ID` ASC)  COMMENT '',
  INDEX `fk_USERS_has_GROUPS_USERS_idx` (`USERS_USER_ID` ASC)  COMMENT '',
  CONSTRAINT `fk_USERS_has_GROUPS_USERS`
    FOREIGN KEY (`USERS_USER_ID`)
    REFERENCES `group_communicator`.`USERS` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USERS_has_GROUPS_GROUPS1`
    FOREIGN KEY (`GROUPS_GROUP_ID`)
    REFERENCES `group_communicator`.`GROUPS` (`GROUP_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
