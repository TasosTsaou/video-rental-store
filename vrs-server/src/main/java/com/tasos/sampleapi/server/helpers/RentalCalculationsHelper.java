package com.tasos.sampleapi.server.helpers;

import org.joda.time.DateTime;
import org.joda.time.Period;

import com.tasos.sampleapi.common.dataobjects.RentalDTO;
import com.tasos.sampleapi.server.config.VideoRentalStoreProperties;
import com.tasos.sampleapi.server.domain.entities.Film;
import com.tasos.common.enums.FilmType;

public class RentalCalculationsHelper {

    private VideoRentalStoreProperties vrsProps;

    public RentalCalculationsHelper() {

    }

    public int calculateBonus(RentalDTO rentalDTO, Film film) {
        int result = 0;
        String filmType = film.getType();
        // calculate the bonus depending on film type:
        if (filmType.equalsIgnoreCase(FilmType.newRelease.name())) {
            result = vrsProps.getNewReleaseBonusPoints();
        } else if (filmType.equalsIgnoreCase(FilmType.regularFilm.name())) {
            result = vrsProps.getRegularFilmBonusPoints();
        } else if (filmType.equalsIgnoreCase(FilmType.oldFilm.name())) {
            result = vrsProps.getOldFilmBonusPoints();
        } else {
            throw new RuntimeException("The film type of the rental " + rentalDTO.getId() + "is not expected.");
        }
        return result;
    }

    public double calculatePrice(RentalDTO rentalDTO, Film film) {
        double result = 0.0;
        double basicCost = 0.0;
        double additionalCost = 0.0;
        String filmType = film.getType();
        // Find the total days the film has been rented:
        Period period = new Period(rentalDTO.getDateRented(), rentalDTO.getDateReturned());
        int daysRented = period.getDays();
        // calculate the price based on film type and days rented:
        if (filmType.equalsIgnoreCase(FilmType.newRelease.name())) {
            basicCost = vrsProps.getPremiumPrice();
            int rechargeIntervals = calculateSurchargeIntervals(vrsProps.getDaysOnStartingPriceNewRelease(),
                    vrsProps.getRechargeIntervalNewRelease(), daysRented);
            additionalCost = vrsProps.getPremiumPrice() * rechargeIntervals;
        } else if (filmType.equalsIgnoreCase(FilmType.regularFilm.name())) {
            basicCost = vrsProps.getBasicPrice();
            int rechargeIntervals = calculateSurchargeIntervals(vrsProps.getDaysOnStartingPriceRegularFilm(),
                    vrsProps.getRechargeIntervalRegularFilm(), daysRented);
            additionalCost = vrsProps.getBasicPrice() * rechargeIntervals;
        } else if (filmType.equalsIgnoreCase(FilmType.oldFilm.name())) {
            basicCost = vrsProps.getBasicPrice();
            int rechargeIntervals = calculateSurchargeIntervals(vrsProps.getDaysOnStartingPriceOldFilm(),
                    vrsProps.getRechargeIntervalOldFilm(), daysRented);
            additionalCost = vrsProps.getBasicPrice() * rechargeIntervals;
        } else {
            throw new RuntimeException("The film type of the rental " + rentalDTO.getId() + "is not expected.");
        }

        return basicCost + additionalCost;
    }

    public int calculateSurchargeIntervals(Integer normalRentDays, int daysOfInterval, int daysRented) {
        double rechargeIntervals = 0;
        if (daysRented > normalRentDays) {
            double approximateIntervals = (daysRented - normalRentDays) / daysOfInterval;
            rechargeIntervals = (daysRented - normalRentDays) % normalRentDays == 0 ? Math.floor(approximateIntervals)
                    : Math.floor(approximateIntervals) + 1;

        }
        return (int) rechargeIntervals;
    }

    public DateTime calculateRegularReturnDate(RentalDTO rentalDTO, Film film) {
        DateTime regularReturnDate = null;
        String filmType = film.getType();
        if (filmType.equalsIgnoreCase(FilmType.newRelease.name())) {
            regularReturnDate = rentalDTO.getDateRented().plusDays(vrsProps.getDaysOnStartingPriceNewRelease());
        } else if (filmType.equalsIgnoreCase(FilmType.regularFilm.name())) {
            regularReturnDate = rentalDTO.getDateRented().plusDays(vrsProps.getDaysOnStartingPriceRegularFilm());
        } else if (filmType.equalsIgnoreCase(FilmType.oldFilm.name())) {
            regularReturnDate = rentalDTO.getDateRented().plusDays(vrsProps.getDaysOnStartingPriceOldFilm());
        } else {
            throw new RuntimeException("The film type of the rental " + rentalDTO.getId() + "is not expected.");
        }

        return regularReturnDate;
    }

    public VideoRentalStoreProperties getVrsProps() {
        return vrsProps;
    }

    public void setVrsProps(VideoRentalStoreProperties vrsProps) {
        this.vrsProps = vrsProps;
    }

}
