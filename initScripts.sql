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
-- Table `mydb`.`Platforms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Platforms` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Platforms` (
  `idPlatforms` INT NOT NULL,
  `platformName` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idPlatforms`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`App`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`App` ;

CREATE TABLE IF NOT EXISTS `mydb`.`App` (
  `appName` VARCHAR(45) NULL DEFAULT NULL,
  `appDeveloper` VARCHAR(45) NULL DEFAULT NULL,
  `appDescription` LONGTEXT NULL DEFAULT NULL,
  `appUrl` LONGTEXT NULL DEFAULT NULL,
  `appRating` DOUBLE NULL DEFAULT NULL,
  `appCost` DOUBLE NULL DEFAULT NULL,
  `appPlatform` INT NULL DEFAULT NULL,
  `appVersion` DOUBLE NULL DEFAULT NULL,
  `appAvailable` TINYINT(1) NULL DEFAULT NULL,
  `appId` VARCHAR(45) NOT NULL,
  `appApproved` TINYINT(1) NULL DEFAULT NULL,
  `appLocalImage` LONGTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`appId`),
  INDEX `Platform_idx` (`appPlatform` ASC),
  CONSTRAINT `Platform`
    FOREIGN KEY (`appPlatform`)
    REFERENCES `mydb`.`Platforms` (`idPlatforms`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`User` ;

CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`));


-- -----------------------------------------------------
-- Table `mydb`.`AppComments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`AppComments` ;

CREATE TABLE IF NOT EXISTS `mydb`.`AppComments` (
  `commentId` INT NOT NULL,
  `appComment` LONGTEXT NOT NULL,
  `appId` VARCHAR(45) NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `username` VARCHAR(45) NULL,
  `approvalComment` TINYINT(1) NOT NULL,
  PRIMARY KEY (`commentId`),
  INDEX `appId_idx` (`appId` ASC),
  INDEX `username_idx` (`username` ASC),
  CONSTRAINT `appId`
    FOREIGN KEY (`appId`)
    REFERENCES `mydb`.`App` (`appId`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT `username`
    FOREIGN KEY (`username`)
    REFERENCES `mydb`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`UserTypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`UserTypes` ;

CREATE TABLE IF NOT EXISTS `mydb`.`UserTypes` (
  `UserType` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`UserType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`UserRoles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`UserRoles` ;

CREATE TABLE IF NOT EXISTS `mydb`.`UserRoles` (
  `username` VARCHAR(16) NOT NULL,
  `UserType` VARCHAR(15) NOT NULL,
  INDEX `User_idx` (`username` ASC),
  PRIMARY KEY (`username`, `UserType`),
  INDEX `UserType_idx` (`UserType` ASC),
  CONSTRAINT `User`
    FOREIGN KEY (`username`)
    REFERENCES `mydb`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `UserType`
    FOREIGN KEY (`UserType`)
    REFERENCES `mydb`.`UserTypes` (`UserType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`Platforms`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Platforms` (`idPlatforms`, `platformName`) VALUES (8888, 'Apple Store');
INSERT INTO `mydb`.`Platforms` (`idPlatforms`, `platformName`) VALUES (9999, 'Microsoft Store');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`App`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`App` (`appName`, `appDeveloper`, `appDescription`, `appUrl`, `appRating`, `appCost`, `appPlatform`, `appVersion`, `appAvailable`, `appId`, `appApproved`, `appLocalImage`) VALUES ('Cool App', 'Nathan and Team', 'It\'s a decent app', 'thisisanappurl.com', 3.2, 4.99, 9999, 1, 1, '12345', 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`User` (`username`, `email`, `password`, `create_time`, `firstName`, `lastName`) VALUES ('root', 'root@root.com', 'password', NULL, 'root', 'root');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`AppComments`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`AppComments` (`commentId`, `appComment`, `appId`, `create_time`, `username`, `approvalComment`) VALUES (2345, 'This is the greatest app ever!', '12345', NULL, 'root', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`UserTypes`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`UserTypes` (`UserType`) VALUES ('Admin');
INSERT INTO `mydb`.`UserTypes` (`UserType`) VALUES ('Moderator');
INSERT INTO `mydb`.`UserTypes` (`UserType`) VALUES ('General User');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`UserRoles`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`UserRoles` (`username`, `UserType`) VALUES ('root', 'Admin');

COMMIT;

