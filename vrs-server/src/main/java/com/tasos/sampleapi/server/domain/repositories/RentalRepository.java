package com.tasos.sampleapi.server.domain.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.tasos.sampleapi.server.domain.entities.Rental;

public interface RentalRepository extends PagingAndSortingRepository<Rental, Integer> {

    List<Rental> findByCustomerId(Integer customerId);

    List<Rental> findByFilmId(Integer filmId);

    @Query("SELECT r FROM Rental r WHERE r.film.id in ?1 AND r.customer.id =?2 AND r.returned = ?3")
    List<Rental> findByFilmIdAndCustomerIdAndReturned(Set<Integer> returnedFilmIds, Integer customerId, Boolean returned);

}
