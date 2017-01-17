package com.tasos.sampleapi.server.config;


public class VideoRentalStoreProperties {

    
    private double basicPrice;

    private double premiumPrice;

    private int daysOnStartingPriceNewRelease;

    private int rechargeIntervalNewRelease;

    private int daysOnStartingPriceRegularFilm;

    private int rechargeIntervalRegularFilm;

    private int daysOnStartingPriceOldFilm;

    private int rechargeIntervalOldFilm;

    private int newReleaseBonusPoints;

    private int regularFilmBonusPoints;

    private int oldFilmBonusPoints;

    public VideoRentalStoreProperties() {

    }

    /**
     * @param basicPrice
     * @param premiumPrice
     * @param daysOnStartingPriceNewRelease
     * @param rechargeIntervalNewRelease
     * @param daysOnStartingPriceRegularFilm
     * @param rechargeIntervalRegularFilm
     * @param daysOnStartingPriceOldFilm
     * @param rechargeIntervalOldFilm
     * @param newReleaseBonusPoints
     * @param regularFilmBonusPoints
     * @param oldFilmBonusPoints
     */
    public VideoRentalStoreProperties(double basicPrice, double premiumPrice, int daysOnStartingPriceNewRelease,
            int rechargeIntervalNewRelease, int daysOnStartingPriceRegularFilm, int rechargeIntervalRegularFilm,
            int daysOnStartingPriceOldFilm, int rechargeIntervalOldFilm, int newReleaseBonusPoints,
            int regularFilmBonusPoints, int oldFilmBonusPoints) {
        super();
        this.basicPrice = basicPrice;
        this.premiumPrice = premiumPrice;
        this.daysOnStartingPriceNewRelease = daysOnStartingPriceNewRelease;
        this.rechargeIntervalNewRelease = rechargeIntervalNewRelease;
        this.daysOnStartingPriceRegularFilm = daysOnStartingPriceRegularFilm;
        this.rechargeIntervalRegularFilm = rechargeIntervalRegularFilm;
        this.daysOnStartingPriceOldFilm = daysOnStartingPriceOldFilm;
        this.rechargeIntervalOldFilm = rechargeIntervalOldFilm;
        this.newReleaseBonusPoints = newReleaseBonusPoints;
        this.regularFilmBonusPoints = regularFilmBonusPoints;
        this.oldFilmBonusPoints = oldFilmBonusPoints;
    }

    public double getBasicPrice() {
        return basicPrice;
    }

    public double getPremiumPrice() {
        return premiumPrice;
    }

    public int getDaysOnStartingPriceNewRelease() {
        return daysOnStartingPriceNewRelease;
    }

    public int getRechargeIntervalNewRelease() {
        return rechargeIntervalNewRelease;
    }

    public int getDaysOnStartingPriceRegularFilm() {
        return daysOnStartingPriceRegularFilm;
    }

    public int getRechargeIntervalRegularFilm() {
        return rechargeIntervalRegularFilm;
    }

    public int getDaysOnStartingPriceOldFilm() {
        return daysOnStartingPriceOldFilm;
    }

    public int getRechargeIntervalOldFilm() {
        return rechargeIntervalOldFilm;
    }

    public int getNewReleaseBonusPoints() {
        return newReleaseBonusPoints;
    }

    public int getRegularFilmBonusPoints() {
        return regularFilmBonusPoints;
    }

    public int getOldFilmBonusPoints() {
        return oldFilmBonusPoints;
    }

}
