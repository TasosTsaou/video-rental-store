package com.tasos.sampleapi.server.sampleapi.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.tasos.sampleapi.common.dataobjects.RentalDTO;
import com.tasos.sampleapi.common.dataobjects.RentalResultDTO;
import com.tasos.sampleapi.common.dataobjects.ReturnFilmsResultDTO;
import com.tasos.sampleapi.server.domain.entities.Customer;
import com.tasos.sampleapi.server.domain.entities.Film;
import com.tasos.sampleapi.server.domain.entities.Rental;
import com.tasos.sampleapi.server.domain.repositories.CustomerRepository;
import com.tasos.sampleapi.server.domain.repositories.FilmRepository;
import com.tasos.sampleapi.server.domain.repositories.RentalRepository;
import com.tasos.sampleapi.server.helpers.RentalCalculationsHelper;
import com.tasos.sampleapi.server.sampleapi.RentalManagementService;

@Service
public class RentalManagementServiceImpl implements RentalManagementService {

    @Autowired
    RentalRepository rentalRepo;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    FilmRepository filmRepo;

    @Autowired
    RentalCalculationsHelper calculationsHelper;

    public RentalManagementServiceImpl() {
    }

    @Override
    public RentalDTO createRental(RentalDTO rentalDTO) {
        Film film = filmRepo.findById(rentalDTO.getFilmId());
        rentalDTO.setDateReturned(calculationsHelper.calculateRegularReturnDate(rentalDTO, film));
        rentalDTO.setPrice(calculationsHelper.calculatePrice(rentalDTO, film));
        Customer rentalCustomer = customerRepo.findById(rentalDTO.getCustomerId());
        rentalCustomer.setBonusPoints(rentalCustomer.getBonusPoints()
                + this.calculationsHelper.calculateBonus(rentalDTO, film));
        customerRepo.save(rentalCustomer);
        final Rental newRental = new Rental();
        applyRentalDTOPropertiesToRental(rentalDTO, newRental);
        Rental savedRental = rentalRepo.save(newRental);
        applyRentalPropertiesToRentalDTO(rentalDTO, savedRental);
        return rentalDTO;
    }

    @Override
    public RentalDTO updateRental(RentalDTO rentalDTO) {
        Rental foundRental = rentalRepo.findOne(rentalDTO.getId());
        Assert.notNull(foundRental, "Rental with id:" + rentalDTO.getId() + " does not exist");
        applyRentalDTOPropertiesToRental(rentalDTO, foundRental);
        Rental updatedRental = rentalRepo.save(foundRental);
        applyRentalPropertiesToRentalDTO(rentalDTO, updatedRental);
        return rentalDTO;
    }

    @Override
    public RentalDTO getRentalById(int rentalId) {
        Rental foundRental = rentalRepo.findOne(rentalId);
        Assert.notNull(foundRental, "Rental with id:" + rentalId + " does not exist");
        RentalDTO foundRentalDTO = new RentalDTO();
        applyRentalPropertiesToRentalDTO(foundRentalDTO, foundRental);
        return foundRentalDTO;
    }

    @Override
    public List<RentalDTO> getRentalsByFilmId(int filmId) {

        return rentalRepo.findByFilmId(filmId).stream().map(rental -> rental.toDTO()).collect(Collectors.toList());
    }

    @Override
    public List<RentalDTO> getRentalsByCustomerId(int customerId) {
        return rentalRepo.findByCustomerId(customerId).stream().map(rental -> rental.toDTO())
                .collect(Collectors.toList());
    }

    @Override
    public Double calculateRentalPrice(int rentalId) {
        RentalDTO rentalDTO = this.getRentalById(rentalId);
        Film film = filmRepo.findById(rentalDTO.getFilmId());
        return calculationsHelper.calculatePrice(rentalDTO, film);
    }

    @Override
    public Integer awardBonusPoints(int rentalId) {
        RentalDTO rentalDTO = this.getRentalById(rentalId);
        Film film = filmRepo.findById(rentalDTO.getFilmId());
        Customer customer = customerRepo.findById(rentalDTO.getCustomerId());
        Integer bonus = calculationsHelper.calculateBonus(rentalDTO, film);
        customer.setBonusPoints(bonus);
        customerRepo.save(customer);
        return bonus;
    }

    @Override
    public RentalResultDTO rentFilms(List<RentalDTO> filmsList) {
        double totalPrice = filmsList.stream().map(rental -> {
            RentalDTO savedRental = this.createRental(rental);
            return savedRental;
        }).reduce(0.0, (sum, savedRental) -> sum += savedRental.getPrice(), (sum1, sum2) -> sum1 + sum2);

        RentalResultDTO rentalResultDTO = new RentalResultDTO();
        rentalResultDTO.setInitialRentalCharges(totalPrice);

        return rentalResultDTO;
    }

    @Override
    public ReturnFilmsResultDTO returnFilms(Set<Integer> returnedFilmIds, int customerId, DateTime returnDate) {
        // List<Film> returnedFilms = filmRepo.findByIdIn(returnedFilmIds);
        List<Rental> rentals = rentalRepo.findByFilmIdAndCustomerIdAndReturned(returnedFilmIds, customerId, false);
        double totalSurcharges = rentals.stream().map(rental -> {
            Film returnedFilm = filmRepo.findById(rental.getFilm().getId());
            double initialCharge = rental.getPrice();
            rental.setDateReturned(returnDate);
            double totalCharge = calculationsHelper.calculatePrice(rental.toDTO(), returnedFilm);
            rental.setPrice(totalCharge);
            rental.setReturned(true);
            rentalRepo.save(rental);
            double rentalSurcharge = totalCharge - initialCharge;
            return rentalSurcharge;
        }).reduce(0.0, (sum, rentalSurcharge) -> sum += rentalSurcharge, (sum1, sum2) -> sum1 + sum2);

        ReturnFilmsResultDTO returnFilmsResultDTO = new ReturnFilmsResultDTO();
        returnFilmsResultDTO.setSurcharges(totalSurcharges);

        return returnFilmsResultDTO;
    }

    /* private helper methods */

    private void applyRentalDTOPropertiesToRental(RentalDTO rentalDTO, Rental rental) {
        rental.setCustomer(customerRepo.findById(rentalDTO.getCustomerId()));
        rental.setFilm(filmRepo.findById(rentalDTO.getFilmId()));
        rental.setDateRented(rentalDTO.getDateRented());
        rental.setDateReturned(rentalDTO.getDateReturned());
        rental.setPrice(rentalDTO.getPrice());
        rental.setReturned(rentalDTO.isReturned());
    }

    private void applyRentalPropertiesToRentalDTO(RentalDTO rentalDTO, Rental rental) {
        rentalDTO.setId(rental.getId());
        rentalDTO.setCustomerId(rental.getCustomer().getId());
        rentalDTO.setFilmId(rental.getFilm().getId());
        rentalDTO.setDateRented(rental.getDateRented());
        rentalDTO.setPrice(rental.getPrice());
        rentalDTO.setDateReturned(rental.getDateReturned());
        rentalDTO.setReturned(rental.isReturned());
    }

}
