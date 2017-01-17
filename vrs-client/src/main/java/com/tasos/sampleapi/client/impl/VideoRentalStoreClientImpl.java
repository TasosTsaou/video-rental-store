package com.tasos.sampleapi.client.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tasos.sampleapi.client.VideoRentalStoreClient;
import com.tasos.sampleapi.client.config.UrlRootInfo;
import com.tasos.sampleapi.common.dataobjects.CustomerDTO;
import com.tasos.sampleapi.common.dataobjects.FilmDTO;
import com.tasos.sampleapi.common.dataobjects.RentalListDTO;
import com.tasos.sampleapi.common.dataobjects.RentalResultDTO;
import com.tasos.sampleapi.common.dataobjects.ReturnFilmsDTO;
import com.tasos.sampleapi.common.dataobjects.ReturnFilmsResultDTO;
import com.tasos.common.constants.Constants;

@Service
public class VideoRentalStoreClientImpl implements VideoRentalStoreClient {

    private Logger logger = (Logger) LoggerFactory.getLogger(VideoRentalStoreClientImpl.class);

    @Autowired
    private UrlRootInfo urlRootInfo;

    RestTemplate restTemplate = new RestTemplate();

    private String host;
    private String port;

    public VideoRentalStoreClientImpl() {
        // TODO Auto-generated constructor stub
    }

    @PostConstruct
    public void init() {
        host = urlRootInfo.getHost();
        port = urlRootInfo.getPort();
    }

    private String getServiceUrlRoot() {
        return "http://" + host + ":" + port + "/" + Constants.CONTEXT;
    }

    @Override
    public RentalResultDTO rentFilms(RentalListDTO rentalList) {
        final String url = getServiceUrlRoot() + "/" + Constants.RENT_FILMS;
        logger.debug("API call: " + url);
        logger.debug("Request: " + rentalList.toString());
        RentalResultDTO rentalResult = restTemplate.postForObject(url, rentalList, RentalResultDTO.class);
        logger.debug("Response: " + rentalResult.getInitialRentalCharges());
        return rentalResult;
    }

    @Override
    public ReturnFilmsResultDTO returnFilms(ReturnFilmsDTO returnFilmsDTO) {
        final String url = getServiceUrlRoot() + "/" + Constants.RETURN;
        logger.debug("API call: " + url);
        logger.debug("Request: " + returnFilmsDTO.toString());
        ReturnFilmsResultDTO returnResult = restTemplate.postForObject(url, returnFilmsDTO, ReturnFilmsResultDTO.class);
        logger.debug("Response: " + returnResult.getSurcharges());
        return returnResult;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        final String url = getServiceUrlRoot() + "/" + Constants.NEW + "/" + Constants.CUSTOMER_CTX;
        logger.debug("API call: " + url);
        logger.debug("Request: " + customerDTO.toString());
        CustomerDTO createdCustomer = restTemplate.postForObject(url, customerDTO, CustomerDTO.class);
        logger.debug("Response: " + createdCustomer.getId());
        return createdCustomer;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        final String url = getServiceUrlRoot() + "/" + Constants.UPDATE + "/" + Constants.CUSTOMER_CTX;
        logger.debug("API call: " + url);
        logger.debug("Request: " + customerDTO.toString());
        CustomerDTO updatedCustomer = restTemplate.postForObject(url, customerDTO, CustomerDTO.class);
        logger.debug("Response: " + updatedCustomer.getId());
        return updatedCustomer;
    }

    @Override
    public CustomerDTO findCustomer(CustomerDTO customerDTO) {
        final String url = getServiceUrlRoot() + "/" + Constants.FIND + "/" + Constants.CUSTOMER_CTX;
        logger.debug("API call: " + url);
        logger.debug("Request: " + customerDTO.toString());
        CustomerDTO foundCustomer = restTemplate.postForObject(url, customerDTO, CustomerDTO.class);
        logger.debug("Response: " + foundCustomer.getId());
        return foundCustomer;
    }

    @Override
    public FilmDTO createFilm(FilmDTO filmDTO) {
        final String url = getServiceUrlRoot() + "/" + Constants.NEW + "/" + Constants.FILM_CTX;
        logger.debug("API call: " + url);
        logger.debug("Request: " + filmDTO.toString());
        FilmDTO createdFilm = restTemplate.postForObject(url, filmDTO, FilmDTO.class);
        logger.debug("Response: " + createdFilm.getId());
        return createdFilm;
    }

    @Override
    public FilmDTO updateFilm(FilmDTO filmDTO) {
        final String url = getServiceUrlRoot() + "/" + Constants.UPDATE + "/" + Constants.FILM_CTX;
        logger.debug("API call: " + url);
        logger.debug("Request: " + filmDTO.toString());
        FilmDTO updatedFilm = restTemplate.postForObject(url, filmDTO, FilmDTO.class);
        logger.debug("Response: " + updatedFilm.getId());
        return updatedFilm;
    }

    @Override
    public FilmDTO findFilm(FilmDTO filmDTO) {
        final String url = getServiceUrlRoot() + "/" + Constants.FIND + "/" + Constants.FILM_CTX;
        logger.debug("API call: " + url);
        logger.debug("Request: " + filmDTO.toString());
        FilmDTO foundFilm = restTemplate.postForObject(url, filmDTO, FilmDTO.class);
        logger.debug("Response: " + foundFilm.getId());
        return foundFilm;
    }

}
