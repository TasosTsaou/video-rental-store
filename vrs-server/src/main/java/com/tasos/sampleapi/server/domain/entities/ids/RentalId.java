package com.tasos.sampleapi.server.domain.entities.ids;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// @Embeddable
public class RentalId implements Serializable {

    private static final long serialVersionUID = 1L;

    // @Column(name = "customers_id")
    private Integer customer;

    // (name = "films_id")
    private Integer film;

    public RentalId() {
    }

    public Integer getCustomersId() {
        return customer;
    }

    public void setCustomersId(Integer customersId) {
        this.customer = customersId;
    }

    public Integer getFilmsId() {
        return film;
    }

    public void setFilmsId(Integer filmsId) {
        this.film = filmsId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RentalId that = (RentalId) o;

        if (customer != null ? !customer.equals(that.customer) : that.customer != null) {
            return false;
        }
        return !(film != null ? !film.equals(that.film) : that.film != null);

    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = customer != null ? customer.hashCode() : 0;
        result = 31 * result + (film != null ? film.hashCode() : 0);
        return result;
    }

}
