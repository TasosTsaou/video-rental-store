package com.tasos.sampleapi.server.domain.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tasos.sampleapi.server.domain.entities.Film;

public interface FilmRepository extends PagingAndSortingRepository<Film, Integer> {

    /**
     * Finds the <code>Film</code> corresponding to the supplied identifier.
     *
     * @param filmId
     *            the identifier of the <code>Film</code> to search for.
     * @return the <code>Film</code> corresponding to the supplied
     *         identifier.
     */
    Film findById(Integer filmId);
    
    List<Film> findByIdIn(Set<Integer> id);
}
