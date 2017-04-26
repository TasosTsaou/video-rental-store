package com.tasos.sampleapi.common.dataobjects;

import java.time.Instant;
import java.util.Set;

public class ReturnFilmsDTO {

    private Set<Integer> returnedFilmIds;

    private int customerId;

    private Instant returnDate;

    public ReturnFilmsDTO() {

    }

    public Set<Integer> getReturnedFilmIds() {
        return returnedFilmIds;
    }

    public void setReturnedFilmIds(Set<Integer> returnedFilmIds) {
        this.returnedFilmIds = returnedFilmIds;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Instant getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Instant returnDate) {
        this.returnDate = returnDate;
    }

}
