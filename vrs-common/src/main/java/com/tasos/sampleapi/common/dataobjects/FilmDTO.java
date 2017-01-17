package com.tasos.sampleapi.common.dataobjects;

import java.util.ArrayList;
import java.util.List;

public class FilmDTO {

    private Integer id;

    private String type;

    private List<RentalDTO> rentals = new ArrayList<>();

    public FilmDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<RentalDTO> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalDTO> rentals) {
        this.rentals = rentals;
    }

}
