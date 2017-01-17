package com.tasos.sampleapi.common.dataobjects;

import java.util.Set;

import org.joda.time.DateTime;

public class ReturnFilmsDTO {

    private Set<Integer> returnedFilmIds;

    private int customerId;

    private DateTime returnDate;

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

    public DateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(DateTime returnDate) {
        this.returnDate = returnDate;
    }

}
