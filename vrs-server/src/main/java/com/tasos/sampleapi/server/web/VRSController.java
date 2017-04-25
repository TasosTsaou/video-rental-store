package com.tasos.sampleapi.server.web;

import javax.validation.Valid;

import com.tasos.sampleapi.server.services.CustomerManagementService;
import com.tasos.sampleapi.server.services.FilmManagementService;
import com.tasos.sampleapi.server.services.RentalManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasos.sampleapi.common.dataobjects.CustomerDTO;
import com.tasos.sampleapi.common.dataobjects.FilmDTO;
import com.tasos.sampleapi.common.dataobjects.RentalDTO;
import com.tasos.sampleapi.common.dataobjects.RentalListDTO;
import com.tasos.sampleapi.common.dataobjects.RentalResultDTO;
import com.tasos.sampleapi.common.dataobjects.ReturnFilmsDTO;
import com.tasos.sampleapi.common.dataobjects.ReturnFilmsResultDTO;

import com.tasos.common.constants.Constants;

@RestController
@RequestMapping("/" + Constants.CONTEXT)
public class VRSController implements Constants {

    private static final Logger logger = LoggerFactory.getLogger(VRSController.class);

    @Autowired
    private CustomerManagementService customerService;

    @Autowired
    FilmManagementService filmService;

    @Autowired
    RentalManagementService rentalService;

    public VRSController() {
    }

    @ResponseBody
    @RequestMapping(value = "/" + NEW + "/" + CUSTOMER_CTX, method = RequestMethod.POST)
    public CustomerDTO createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        logger.debug("create customer: {}", customerDTO);
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        logger.debug("created customer: {}", createdCustomer);
        return createdCustomer;
    }

    @ResponseBody
    @RequestMapping(value = "/" + UPDATE + "/" + CUSTOMER_CTX, method = RequestMethod.POST)
    public CustomerDTO updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        logger.debug("update customer: {}", customerDTO);
        CustomerDTO updateCustomer = customerService.updateCustomer(customerDTO);
        logger.debug("updated customer: {}", updateCustomer);
        return updateCustomer;
    }

    @ResponseBody
    @RequestMapping(value = "/" + FIND + "/" + CUSTOMER_CTX, method = RequestMethod.POST)
    public CustomerDTO findCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        logger.debug("Find customer: {}", customerDTO);
        CustomerDTO foundCustomer = customerService.getCustomerById(customerDTO.getId());
        logger.debug("found customer: {}", foundCustomer);
        return foundCustomer;
    }

    @ResponseBody
    @RequestMapping(value = "/" + NEW + "/" + FILM_CTX, method = RequestMethod.POST)
    public FilmDTO createFilm(@Valid @RequestBody FilmDTO filmDTO) {
        logger.debug("create film: {}", filmDTO);
        FilmDTO createdFilm = filmService.createFilm(filmDTO);
        logger.debug("created film: {}", createdFilm);
        return createdFilm;
    }

    @ResponseBody
    @RequestMapping(value = "/" + UPDATE + "/" + FILM_CTX, method = RequestMethod.POST)
    public FilmDTO updateFilm(@Valid @RequestBody FilmDTO filmDTO) {
        logger.debug("update film: {}", filmDTO);
        FilmDTO updateFilm = filmService.updateFilm(filmDTO);
        logger.debug("updated film: {}", updateFilm);
        return updateFilm;
    }

    @ResponseBody
    @RequestMapping(value = "/" + FIND + "/" + FILM_CTX, method = RequestMethod.POST)
    public FilmDTO findFilm(@Valid @RequestBody FilmDTO filmDTO) {
        logger.debug("Find film: {}", filmDTO);
        FilmDTO foundFilm = filmService.getFilmById(filmDTO.getId());
        logger.debug("found film: {}", foundFilm);
        return foundFilm;
    }

    @ResponseBody
    @RequestMapping(value = "/" + NEW + "/" + RENTAL_CTX, method = RequestMethod.POST)
    public RentalDTO createRental(@Valid @RequestBody RentalDTO rentalDTO) {
        logger.debug("create rental: {}", rentalDTO);
        RentalDTO createdRental = rentalService.createRental(rentalDTO);
        logger.debug("created rental {}", createdRental);
        return createdRental;
    }

    @ResponseBody
    @RequestMapping(value = "/" + UPDATE + "/" + RENTAL_CTX, method = RequestMethod.POST)
    public RentalDTO updateaRental(@Valid @RequestBody RentalDTO rentalDTO) {
        logger.debug("update rental: {}", rentalDTO);
        RentalDTO updateRental = rentalService.updateRental(rentalDTO);
        logger.debug("updated rental: {}", updateRental);
        return updateRental;
    }

    @ResponseBody
    @RequestMapping(value = "/" + FIND + "/" + RENTAL_CTX, method = RequestMethod.GET)
    public RentalDTO findRental(@Valid @RequestBody RentalDTO rentalDTO) {
        logger.debug("Find rental: {}", rentalDTO);
        RentalDTO foundRental = rentalService.getRentalById(rentalDTO.getId());
        logger.debug("found rental: {}", foundRental);
        return foundRental;
    }

    @ResponseBody
    @RequestMapping(value = "/" + RENT_FILMS, method = RequestMethod.POST)
    public RentalResultDTO rentFilms(@Valid @RequestBody RentalListDTO rentalList) {
        logger.debug("Register rentals: {}", rentalList.getRentalList().size());
        RentalResultDTO resultDTO = rentalService.rentFilms(rentalList.getRentalList());
        logger.debug("Initial rental cost: {}", resultDTO.getInitialRentalCharges());
        return resultDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/" + RETURN, method = RequestMethod.POST)
    public ReturnFilmsResultDTO returnFilms(@Valid @RequestBody ReturnFilmsDTO returnFilmsDTO) {
        logger.debug("returning films: {}", returnFilmsDTO.getReturnedFilmIds());
        ReturnFilmsResultDTO resultDTO = rentalService.returnFilms(returnFilmsDTO.getReturnedFilmIds(),
                returnFilmsDTO.getCustomerId(), returnFilmsDTO.getReturnDate());
        logger.debug("Surcharges: {}", resultDTO.getSurcharges());
        return resultDTO;
    }

}
