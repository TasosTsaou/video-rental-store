package com.tasos.sampleapi.common.dataobjects;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {

    private Integer id;

    private Integer bonusPoints;

    private List<RentalDTO> rentals = new ArrayList<>();

    public CustomerDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Integer bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public List<RentalDTO> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalDTO> rentals) {
        this.rentals = rentals;
    }

}
