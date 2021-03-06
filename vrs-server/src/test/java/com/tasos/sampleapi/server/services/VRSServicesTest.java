package com.tasos.sampleapi.server.sampleapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tasos.sampleapi.common.dataobjects.CustomerDTO;
import com.tasos.sampleapi.common.dataobjects.FilmDTO;
import com.tasos.sampleapi.common.dataobjects.RentalDTO;
import com.tasos.sampleapi.common.dataobjects.RentalResultDTO;
import com.tasos.sampleapi.common.dataobjects.ReturnFilmsResultDTO;
import com.tasos.sampleapi.server.VRSServer;
import com.tasos.sampleapi.server.helpers.RentalCalculationsHelper;
import com.tasos.common.enums.FilmType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VRSServer.class)
@ComponentScan("com.tasos.sampleapi")
public class VRSServicesTest {

    @Autowired
    CustomerManagementService customerService;

    @Autowired
    FilmManagementService filmService;

    @Autowired
    RentalManagementService rentalService;

    @Autowired
    RentalCalculationsHelper calcHelper;

    CustomerDTO customer1;
    FilmDTO movie1;
    FilmDTO movie2;
    FilmDTO movie3;
    RentalDTO rental1;
    RentalDTO rental2;
    RentalDTO rental3;

    public VRSServicesTest() {

    }

    @Before
    public void setup() {
        customer1 = new CustomerDTO();
        customer1.setBonusPoints(0);

        // customer2 = new CustomerDTO();
        // customer2.setId(2);
        // customer2.setBonusPoints(20);
        //
        movie1 = new FilmDTO();
        movie1.setType(FilmType.newRelease.name());

        movie2 = new FilmDTO();
        movie2.setType(FilmType.regularFilm.name());

        movie3 = new FilmDTO();
        // movie2.setId(2);
        movie3.setType(FilmType.oldFilm.name());

    }

    @Test
    public void testCreateCustomerDTO() {
        CustomerDTO savedCustomer = customerService.createCustomer(customer1);
        assertEquals(0, savedCustomer.getBonusPoints().intValue());
        assertNotEquals(null, savedCustomer.getId());
    }

    @Test
    public void testUpdateCustomerDTO() {
        CustomerDTO savedCustomer = customerService.createCustomer(customer1);
        savedCustomer.setBonusPoints(20);
        CustomerDTO updatedCustomer = customerService.updateCustomer(customer1);
        assertEquals(20, updatedCustomer.getBonusPoints().intValue());
        assertNotEquals(null, updatedCustomer.getId());
    }

    @Test
    public void testGetCustomerById() {
        CustomerDTO savedCustomer = customerService.createCustomer(customer1);

        CustomerDTO foundCustomer = customerService.getCustomerById(customer1.getId());
        assertEquals(savedCustomer.getId(), foundCustomer.getId());
    }

    @Test
    public void testCreateFilmDTO() {
        FilmDTO savedFilm = filmService.createFilm(movie1);
        assertEquals(FilmType.newRelease.name(), savedFilm.getType());
        assertNotEquals(null, savedFilm.getId());
    }

    @Test
    public void testUpdateFilmDTO() {
        FilmDTO savedFilm = filmService.createFilm(movie1);
        savedFilm.setType(FilmType.regularFilm.name());
        FilmDTO updatedFilm = filmService.updateFilm(savedFilm);
        assertEquals(FilmType.regularFilm.name(), updatedFilm.getType());
        assertNotEquals(null, updatedFilm.getId());
    }

    @Test
    public void testGetFilmById() {
        FilmDTO savedFilm = filmService.createFilm(movie1);

        FilmDTO foundFilm = filmService.getFilmById(movie1.getId());
        assertEquals(savedFilm.getId(), foundFilm.getId());
    }

    @Test
    public void testCreateRentalDTO() {
        CustomerDTO savedCustomer = customerService.createCustomer(customer1);
        FilmDTO savedFilm = filmService.createFilm(movie1);
        DateTime dateRented = DateTime.now();
        rental1 = new RentalDTO();
        rental1.setCustomerId(savedCustomer.getId());
        rental1.setFilmId(savedFilm.getId());
        rental1.setDateRented(dateRented);
        RentalDTO savedRental = rentalService.createRental(rental1);
        assertEquals(savedCustomer.getId(), savedRental.getCustomerId());
        assertEquals(40.0, savedRental.getPrice().doubleValue(), 0.01);
        assertEquals(dateRented.plusDays(1), savedRental.getDateReturned());
        CustomerDTO rentalCustomer = customerService.getCustomerById(savedRental.getCustomerId());
        assertEquals(2, rentalCustomer.getBonusPoints().intValue());

    }

    @Test
    public void testRentingFilm() {
        List<RentalDTO> manyRentals = new ArrayList<RentalDTO>();
        CustomerDTO savedCustomer = customerService.createCustomer(customer1);
        FilmDTO savedFilm = filmService.createFilm(movie1);
        DateTime dateRented = DateTime.now();
        rental1 = new RentalDTO();
        rental1.setCustomerId(savedCustomer.getId());
        rental1.setFilmId(savedFilm.getId());
        rental1.setDateRented(dateRented);
        manyRentals.add(rental1);
        RentalResultDTO rentalResult = rentalService.rentFilms(manyRentals);
        double expectedInitialCharge = calcHelper.getVrsProps().getPremiumPrice()
                * calcHelper.getVrsProps().getDaysOnStartingPriceNewRelease();

        assertEquals(expectedInitialCharge, rentalResult.getInitialRentalCharges(), 0.01);

    }

    @Test
    public void testRentingManyFilms() {
        List<RentalDTO> manyRentals = new ArrayList<RentalDTO>();
        CustomerDTO savedCustomer = customerService.createCustomer(customer1);
        FilmDTO savedFilm = filmService.createFilm(movie1);
        DateTime dateRented = DateTime.now();
        rental1 = new RentalDTO();
        rental1.setCustomerId(savedCustomer.getId());
        rental1.setFilmId(savedFilm.getId());
        rental1.setDateRented(dateRented);
        manyRentals.add(rental1);
        savedFilm = filmService.createFilm(movie2);
        rental2 = new RentalDTO();
        rental2.setCustomerId(savedCustomer.getId());
        rental2.setFilmId(savedFilm.getId());
        rental2.setDateRented(dateRented);
        manyRentals.add(rental2);
        savedFilm = filmService.createFilm(movie3);
        rental3 = new RentalDTO();
        rental3.setCustomerId(savedCustomer.getId());
        rental3.setFilmId(savedFilm.getId());
        rental3.setDateRented(dateRented);
        manyRentals.add(rental3);
        RentalResultDTO rentalResult = rentalService.rentFilms(manyRentals);
        double expectedInitialCharge = calcHelper.getVrsProps().getPremiumPrice()
                + calcHelper.getVrsProps().getBasicPrice() * 2;
        assertEquals(expectedInitialCharge, rentalResult.getInitialRentalCharges(), 0.01);
        CustomerDTO bonusCustomer = customerService.getCustomerById(savedCustomer.getId());
        assertEquals(4, bonusCustomer.getBonusPoints().intValue());

    }

    @Test
    public void testUpdateRentalDTO() {
        CustomerDTO savedCustomer = customerService.createCustomer(customer1);
        FilmDTO savedFilm = filmService.createFilm(movie1);
        rental1 = new RentalDTO();
        rental1.setCustomerId(savedCustomer.getId());
        rental1.setFilmId(savedFilm.getId());
        rental1.setDateRented(DateTime.now().minusDays(3));
        rental1.setPrice(2.0);
        RentalDTO savedRental = rentalService.createRental(rental1);
        DateTime dateReturned = DateTime.now();
        savedRental.setDateReturned(dateReturned);
        RentalDTO updatedRental = rentalService.updateRental(savedRental);
        assertEquals(savedRental.getDateReturned(), updatedRental.getDateReturned());
        assertNotEquals(null, updatedRental.getId());
    }

    @Test
    public void testGetRentalById() {
        CustomerDTO savedCustomer = customerService.createCustomer(customer1);
        FilmDTO savedFilm = filmService.createFilm(movie1);
        rental1 = new RentalDTO();
        rental1.setCustomerId(savedCustomer.getId());
        rental1.setFilmId(savedFilm.getId());
        rental1.setDateRented(DateTime.now().minusDays(3));
        RentalDTO savedRental = rentalService.createRental(rental1);

        RentalDTO foundRental = rentalService.getRentalById(savedRental.getId());
        assertEquals(savedRental.getId(), foundRental.getId());
    }

    @Test
    public void testReturningFilmOnTime() {
        CustomerDTO savedCustomer = customerService.createCustomer(customer1);
        FilmDTO savedFilm = filmService.createFilm(movie2);
        rental1 = new RentalDTO();
        rental1.setCustomerId(savedCustomer.getId());
        rental1.setFilmId(savedFilm.getId());
        rental1.setDateRented(DateTime.now().minusDays(3));
        RentalDTO savedRental = rentalService.createRental(rental1);

        Set<Integer> filmsToReturn = new HashSet<Integer>();
        filmsToReturn.add(savedRental.getFilmId());
        DateTime dateReturned = DateTime.now();
        ReturnFilmsResultDTO returnResult = rentalService.returnFilms(filmsToReturn, savedRental.getCustomerId(),
                dateReturned);

        Period period = new Period(savedRental.getDateRented(), dateReturned);
        int daysRented = period.getDays();
        assertEquals(3, daysRented);
        double expectedSurcharges = 0.0;
        assertEquals(expectedSurcharges, returnResult.getSurcharges(), 0.01);
    }

    @Test
    public void testReturningNewReleaseLate() {
        int setTestDaysRented = 6;
        CustomerDTO savedCustomer = customerService.createCustomer(customer1);
        FilmDTO savedFilm = filmService.createFilm(movie1);
        rental1 = new RentalDTO();
        rental1.setCustomerId(savedCustomer.getId());
        rental1.setFilmId(savedFilm.getId());
        rental1.setDateRented(DateTime.now().minusDays(setTestDaysRented));
        RentalDTO savedRental = rentalService.createRental(rental1);
        double initialPrice = savedRental.getPrice();

        Set<Integer> filmsToReturn = new HashSet<Integer>();
        filmsToReturn.add(savedRental.getFilmId());
        DateTime dateReturned = DateTime.now();
        ReturnFilmsResultDTO returnResult = rentalService.returnFilms(filmsToReturn, savedRental.getCustomerId(),
                dateReturned);

        Period period = new Period(savedRental.getDateRented(), dateReturned);
        int daysRented = period.getDays();
        assertEquals(setTestDaysRented, daysRented);
        int rechargeIntervals = calcHelper.calculateSurchargeIntervals(calcHelper.getVrsProps()
                .getDaysOnStartingPriceNewRelease(), calcHelper.getVrsProps().getRechargeIntervalNewRelease(),
                daysRented);
        assertEquals(setTestDaysRented - 1, rechargeIntervals);
        double expectedSurcharges = calcHelper.getVrsProps().getPremiumPrice() * rechargeIntervals;
        assertEquals(calcHelper.getVrsProps().getPremiumPrice() * rechargeIntervals, expectedSurcharges, 0.01);
        assertEquals(expectedSurcharges, returnResult.getSurcharges(), 0.01);
    }

    @Test
    public void testReturningRegularFilmLate() {
        CustomerDTO savedCustomer = customerService.createCustomer(customer1);
        FilmDTO savedFilm = filmService.createFilm(movie2);
        rental1 = new RentalDTO();
        rental1.setCustomerId(savedCustomer.getId());
        rental1.setFilmId(savedFilm.getId());
        rental1.setDateRented(DateTime.now().minusDays(5));
        RentalDTO savedRental = rentalService.createRental(rental1);
        double initialPrice = savedRental.getPrice();

        Set<Integer> filmsToReturn = new HashSet<Integer>();
        filmsToReturn.add(savedRental.getFilmId());
        DateTime dateReturned = DateTime.now();
        ReturnFilmsResultDTO returnResult = rentalService.returnFilms(filmsToReturn, savedRental.getCustomerId(),
                dateReturned);

        Period period = new Period(savedRental.getDateRented(), dateReturned);
        int daysRented = period.getDays();
        assertEquals(5, daysRented);
        int rechargeIntervals = calcHelper.calculateSurchargeIntervals(calcHelper.getVrsProps()
                .getDaysOnStartingPriceRegularFilm(), calcHelper.getVrsProps().getRechargeIntervalRegularFilm(),
                daysRented);

        double expectedSurcharges = calcHelper.getVrsProps().getBasicPrice() * rechargeIntervals;
        assertEquals(calcHelper.getVrsProps().getBasicPrice(), expectedSurcharges, 0.01);
        assertEquals(expectedSurcharges, returnResult.getSurcharges(), 0.01);
    }

    @Test
    public void testReturningOldFilmLate() {
        CustomerDTO savedCustomer = customerService.createCustomer(customer1);
        FilmDTO savedFilm = filmService.createFilm(movie3);
        rental1 = new RentalDTO();
        rental1.setCustomerId(savedCustomer.getId());
        rental1.setFilmId(savedFilm.getId());
        rental1.setDateRented(DateTime.now().minusDays(6));
        RentalDTO savedRental = rentalService.createRental(rental1);
        double initialPrice = savedRental.getPrice();

        Set<Integer> filmsToReturn = new HashSet<Integer>();
        filmsToReturn.add(savedRental.getFilmId());
        DateTime dateReturned = DateTime.now();
        ReturnFilmsResultDTO returnResult = rentalService.returnFilms(filmsToReturn, savedRental.getCustomerId(),
                dateReturned);

        Period period = new Period(savedRental.getDateRented(), dateReturned);
        int daysRented = period.getDays();
        assertEquals(6, daysRented);
        int rechargeIntervals = calcHelper.calculateSurchargeIntervals(calcHelper.getVrsProps()
                .getDaysOnStartingPriceOldFilm(), calcHelper.getVrsProps().getRechargeIntervalOldFilm(), daysRented);

        double expectedSurcharges = calcHelper.getVrsProps().getBasicPrice() * rechargeIntervals;
        assertEquals(calcHelper.getVrsProps().getBasicPrice(), expectedSurcharges, 0.01);
        assertEquals(expectedSurcharges, returnResult.getSurcharges(), 0.01);
    }

}
