package com.tasos.sampleapi.server.sampleapi;

import com.tasos.sampleapi.common.dataobjects.FilmDTO;

public interface FilmManagementService {

    public FilmDTO createFilm(FilmDTO filmDTO);

    public FilmDTO updateFilm(FilmDTO filmDTO);

    public FilmDTO getFilmById(int filmId);
}
