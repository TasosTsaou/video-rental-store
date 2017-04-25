package com.tasos.sampleapi.server.services.impl;

import com.tasos.sampleapi.server.services.FilmManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.tasos.sampleapi.common.dataobjects.CustomerDTO;
import com.tasos.sampleapi.common.dataobjects.FilmDTO;
import com.tasos.sampleapi.server.domain.entities.Customer;
import com.tasos.sampleapi.server.domain.entities.Film;
import com.tasos.sampleapi.server.domain.repositories.FilmRepository;


@Service
public class FilmManagementServiceImpl implements FilmManagementService {

    @Autowired
    FilmRepository filmRepo;

    public FilmManagementServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public FilmDTO createFilm(FilmDTO filmDTO) {
        final Film film = new Film();
        applyFilmDTOPropertiesToFilm(filmDTO, film);
        Film savedFilm = filmRepo.save(film);
        applyFilmPropertiesToFilmDTO(filmDTO, savedFilm);
        return filmDTO;
    }

    @Override
    public FilmDTO updateFilm(FilmDTO filmDTO) {
        Film foundFilm = filmRepo.findOne(filmDTO.getId());
        Assert.notNull(foundFilm, "Film with id:" + filmDTO.getId() + " does not exist");
        applyFilmDTOPropertiesToFilm(filmDTO, foundFilm);
        Film updatedFilm = filmRepo.save(foundFilm);
        applyFilmPropertiesToFilmDTO(filmDTO, updatedFilm);
        return filmDTO;
    }

    @Override
    public FilmDTO getFilmById(int filmId) {
        Film foundFilm = filmRepo.findOne(filmId);
        Assert.notNull(foundFilm, "Film with id:" + filmId + " does not exist");
        FilmDTO foundFilmDTO = new FilmDTO();
        applyFilmPropertiesToFilmDTO(foundFilmDTO, foundFilm);
        return foundFilmDTO;
    }

    /* private helper methods */

    private void applyFilmDTOPropertiesToFilm(FilmDTO filmDTO, Film film) {
        film.setType(filmDTO.getType());
    }

    private void applyFilmPropertiesToFilmDTO(FilmDTO filmDTO, Film film) {
        filmDTO.setType(film.getType());
        filmDTO.setId(film.getId());
    }

}
