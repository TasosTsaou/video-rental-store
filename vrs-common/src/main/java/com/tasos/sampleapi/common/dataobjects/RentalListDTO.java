package com.tasos.sampleapi.common.dataobjects;

import java.util.List;

public class RentalListDTO {

    private List<RentalDTO> rentalList;

    public RentalListDTO() {

    }

    public List<RentalDTO> getRentalList() {
        return rentalList;
    }

    public void setRentalList(List<RentalDTO> rentalList) {
        this.rentalList = rentalList;
    }

}
