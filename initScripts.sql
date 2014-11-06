SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`App`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`App` ;

CREATE TABLE IF NOT EXISTS `mydb`.`App` (
  `appName` VARCHAR(45) NULL,
  `appDeveloper` VARCHAR(45) NULL,
  `appDescription` VARCHAR(45) NULL,
  `appUrl` VARCHAR(45) NULL,
  `appRating` DOUBLE NULL,
  `appCost` DOUBLE NULL,
  `appPlatform` VARCHAR(45) NULL,
  `appVersion` DOUBLE NULL,
  `appAvailable` TINYINT(1) NULL,
  `appId` VARCHAR(45) NOT NULL,
  `appApproved` TINYINT(1) NULL,
  PRIMARY KEY (`appId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`AppComments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`AppComments` ;

CREATE TABLE IF NOT EXISTS `mydb`.`AppComments` (
  `commentId` INT NOT NULL,
  `appComment` VARCHAR(45) NULL,
  `appId` VARCHAR(45) NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`commentId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`admin` ;

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
DROP TABLE IF EXISTS `mydb`.`moderator` ;

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
DROP TABLE IF EXISTS `mydb`.`user` ;

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
