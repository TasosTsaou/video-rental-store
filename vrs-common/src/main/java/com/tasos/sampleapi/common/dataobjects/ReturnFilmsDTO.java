package com.tasos.sampleapi.common.dataobjects;

import java.time.LocalDateTime;
import java.util.Set;

public class ReturnFilmsDTO {

    private Set<Integer> returnedFilmIds;

    private int customerId;

    private LocalDateTime returnDate;

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

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

}
