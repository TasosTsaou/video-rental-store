package com.tasos.sampleapi.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.tasos.sampleapi.server.helpers.RentalCalculationsHelper;

/**
 * Reads and generates all the properties our video rental store needs to
 * operate.
 * 
 * @author tasos
 */


@Configuration
@ComponentScan("com.tasos.sampleapi")
public class VideoRentalStoreConfiguration {

    @Value("${vrs.basic.price:30}")
    private double basicPrice;

    @Value("${vrs.premium.price:40}")
    private double premiumPrice;

    @Value("${vrs.days.start.new.release:1}")
    private int daysOnStartingPriceNewRelease;

    @Value("${vrs.recharge.interval.new.release:1}")
    private int rechargeIntervalNewRelease;

    @Value("${vrs.days.start.regular.film:3}")
    private int daysOnStartingPriceRegularFilm;

    @Value("${vrs.recharge.interval.regular.film:3}")
    private int rechargeIntervalRegularFilm;

    @Value("${vrs.days.start.old.film:5}")
    private int daysOnStartingPriceOldFilm;

    @Value("${vrs.recharge.interval.old.film:5}")
    private int rechargeIntervalOldFilm;

    @Value("${vrs.bonus.points.new.release:2}")
    private int newReleaseBonusPoints;

    @Value("${vrs.bonus.points.regular.film:1}")
    private int regularFilmBonusPoints;

    @Value("${vrs.bonus.points.old.film:1}")
    private int oldFilmBonusPoints;

    public VideoRentalStoreConfiguration() {

    }

    @Bean
    public RentalCalculationsHelper getRentalCalculationsHelper() {
        VideoRentalStoreProperties videoRentalStoreProperties = new VideoRentalStoreProperties(basicPrice,
                premiumPrice, daysOnStartingPriceNewRelease, rechargeIntervalNewRelease,
                daysOnStartingPriceRegularFilm, rechargeIntervalRegularFilm, daysOnStartingPriceOldFilm,
                rechargeIntervalOldFilm, newReleaseBonusPoints, regularFilmBonusPoints, oldFilmBonusPoints);
        RentalCalculationsHelper rentalCalculationsHelper = new RentalCalculationsHelper();
        rentalCalculationsHelper.setVrsProps(videoRentalStoreProperties);
        return rentalCalculationsHelper;

    }
}
