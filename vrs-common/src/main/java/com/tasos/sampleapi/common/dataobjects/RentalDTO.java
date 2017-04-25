package com.tasos.sampleapi.common.dataobjects;

import org.joda.time.DateTime;

import java.time.LocalDateTime;

public class RentalDTO {

    private Integer id;

    private Integer customerId;

    private Integer filmId;

    private LocalDateTime dateRented;

    private LocalDateTime dateReturned;

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

    public LocalDateTime getDateRented() {
        return dateRented;
    }

    public void setDateRented(LocalDateTime dateRented) {
        this.dateRented = dateRented;
    }

    public LocalDateTime getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDateTime dateReturned) {
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
