SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `video_rental_store` DEFAULT CHARACTER SET utf8 ;
USE `video_rental_store` ;

-- -----------------------------------------------------
-- Table `video_rental_store`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `video_rental_store`.`customers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `bonus_points` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 658
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `video_rental_store`.`films`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `video_rental_store`.`films` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `film_type` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 740
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `video_rental_store`.`rentals`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `video_rental_store`.`rentals` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `customers_id` INT(11) NOT NULL,
  `films_id` INT(11) NOT NULL,
  `date_rented` DATETIME NOT NULL,
  `return_date` DATETIME NULL DEFAULT NULL,
  `price` FLOAT NOT NULL,
  `returned` BINARY(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `fk_customers_has_films_films1_idx` (`films_id` ASC),
  INDEX `fk_customers_has_films_customers_idx` (`customers_id` ASC),
  CONSTRAINT `fk_customers_has_films_customers`
    FOREIGN KEY (`customers_id`)
    REFERENCES `video_rental_store`.`customers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customers_has_films_films1`
    FOREIGN KEY (`films_id`)
    REFERENCES `video_rental_store`.`films` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 503
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
