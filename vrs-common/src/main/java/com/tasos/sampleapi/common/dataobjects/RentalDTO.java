package com.tasos.sampleapi.common.dataobjects;

import org.joda.time.DateTime;

public class RentalDTO {

    private Integer id;

    private Integer customerId;

    private Integer filmId;

    private DateTime dateRented;

    private DateTime dateReturned;

    private Double price;
    
    private boolean returned;

    public RentalDTO() {

    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public DateTime getDateRented() {
        return dateRented;
    }

    public void setDateRented(DateTime dateRented) {
        this.dateRented = dateRented;
    }

    public DateTime getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(DateTime dateReturned) {
        this.dateReturned = dateReturned;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

}
