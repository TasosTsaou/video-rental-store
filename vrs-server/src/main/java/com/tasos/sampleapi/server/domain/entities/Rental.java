package com.tasos.sampleapi.server.domain.entities;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.tasos.sampleapi.common.dataobjects.RentalDTO;
import org.springframework.data.jpa.repository.Temporal;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
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

    @Column(name = "date_rented", columnDefinition = "timestamp")
    @Type(type="timestamp")
    private Timestamp dateRented;

    @Column(name = "return_date", columnDefinition = "timestamp")
    @Type(type="timestamp")
    private Timestamp dateReturned;

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

    public Instant getDateRented() {
        return dateRented.toInstant();
    }

    public void setDateRented(Instant dateRented) {
        this.dateRented = Timestamp.from(dateRented);
    }

    public Instant getDateReturned() {
        return dateReturned.toInstant();
    }

    public void setDateReturned(Instant dateReturned) {
        this.dateReturned = Timestamp.from(dateReturned);
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
        rentalDTO.setDateRented(this.dateRented.toInstant());
        rentalDTO.setDateReturned(this.dateReturned.toInstant());
        rentalDTO.setPrice(this.price);
        rentalDTO.setReturned(this.returned);
        return rentalDTO;
    }

}
