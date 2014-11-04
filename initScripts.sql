SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`AppApproval`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`AppApproval` (
  `appId` INT NOT NULL,
  `appApproved` VARCHAR(45) NULL,
  `comments` VARCHAR(45) NULL,
  PRIMARY KEY (`appId`),
  CONSTRAINT `appId`
    FOREIGN KEY (`appId`)
    REFERENCES `mydb`.`App` (`appId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`App`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`App` (
  `appId` INT NOT NULL,
  `appName` VARCHAR(45) NULL,
  `appDeveloper` VARCHAR(45) NULL,
  `appDescription` VARCHAR(45) NULL,
  `appLocation` VARCHAR(45) NULL,
  `appUrl` VARCHAR(45) NULL,
  `appRating` VARCHAR(45) NULL,
  `appCost` VARCHAR(45) NULL,
  `appPlatform` VARCHAR(45) NULL,
  `appVersion` VARCHAR(45) NULL,
  `appAvailable` VARCHAR(45) NULL,
  PRIMARY KEY (`appId`),
  CONSTRAINT `appId`
    FOREIGN KEY (`appId`)
    REFERENCES `mydb`.`AppApproval` (`appId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`AppComments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`AppComments` (
  `commentId` INT NOT NULL,
  `appComment` VARCHAR(45) NULL,
  `appId` VARCHAR(45) NULL,
  PRIMARY KEY (`commentId`),
  CONSTRAINT `appId`
    FOREIGN KEY (`commentId`)
    REFERENCES `mydb`.`App` (`appId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`admin` (
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  PRIMARY KEY (`username`));


-- -----------------------------------------------------
-- Table `mydb`.`moderator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`moderator` (
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  PRIMARY KEY (`username`));


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  PRIMARY KEY (`username`));


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
