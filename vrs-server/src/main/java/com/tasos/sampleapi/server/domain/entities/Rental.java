package com.tasos.sampleapi.server.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.tasos.sampleapi.common.dataobjects.RentalDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author tasos
 */
@Entity
@Table(name = "rentals")
// @IdClass(RentalId.class)
public class Rental {

    // @EmbeddedId
    // private RentalId rentalId = new RentalId();
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @Id
    @ManyToOne
    @JoinColumn(name = "customers_id", referencedColumnName = "id")
    private Customer customer;

    // @Id
    @ManyToOne
    @JoinColumn(name = "films_id", referencedColumnName = "id")
    private Film film;

    @Column(name = "date_rented", columnDefinition = "datetime")
    private LocalDateTime dateRented;

    @Column(name = "return_date", columnDefinition = "datetime")
    private LocalDateTime dateReturned;

    @Column(name = "price", columnDefinition = "FLOAT")
    private Double price;

    @Column(name = "returned", columnDefinition = "binary")
    private boolean returned;

    public Rental() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
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

    public RentalDTO toDTO() {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(this.id);
        rentalDTO.setCustomerId(this.customer.getId());
        rentalDTO.setFilmId(this.film.getId());
        rentalDTO.setDateRented(this.dateRented);
        rentalDTO.setDateReturned(this.dateReturned);
        rentalDTO.setPrice(this.price);
        rentalDTO.setReturned(this.returned);
        return rentalDTO;
    }

}
