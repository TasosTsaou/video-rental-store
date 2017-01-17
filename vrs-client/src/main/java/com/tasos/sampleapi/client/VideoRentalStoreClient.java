package com.tasos.sampleapi.client;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import com.tasos.sampleapi.common.dataobjects.CustomerDTO;
import com.tasos.sampleapi.common.dataobjects.FilmDTO;
import com.tasos.sampleapi.common.dataobjects.RentalListDTO;
import com.tasos.sampleapi.common.dataobjects.RentalResultDTO;
import com.tasos.sampleapi.common.dataobjects.ReturnFilmsDTO;
import com.tasos.sampleapi.common.dataobjects.ReturnFilmsResultDTO;

public interface VideoRentalStoreClient {

    /**
     * @param rentalList
     * @return
     */
    RentalResultDTO rentFilms(RentalListDTO rentalList);

    /**
     * @param returnFilmsDTO
     * @return
     */
    ReturnFilmsResultDTO returnFilms(ReturnFilmsDTO returnFilmsDTO);

    /**
     * @param customerDTO
     * @return
     */
    CustomerDTO createCustomer(CustomerDTO customerDTO);

    /**
     * @param customerDTO
     * @return
     */
    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    /**
     * @param customerDTO
     * @return
     */
    CustomerDTO findCustomer(CustomerDTO customerDTO);

    /**
     * @param filmDTO
     * @return
     */
    FilmDTO createFilm(FilmDTO filmDTO);

    /**
     * @param filmDTO
     * @return
     */
    FilmDTO updateFilm(FilmDTO filmDTO);

    /**
     * @param filmDTO
     * @return
     */
    FilmDTO findFilm(FilmDTO filmDTO);

}
