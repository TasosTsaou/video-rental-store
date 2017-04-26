package com.tasos.sampleapi.integration.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.tasos.sampleapi.integration.config.VRSClientTestingConfig;
import org.joda.time.Days;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.neo4j.cypher.internal.compiler.v2_1.executionplan.addEagernessIfNecessary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tasos.sampleapi.client.VideoRentalStoreClient;
import com.tasos.sampleapi.common.dataobjects.CustomerDTO;
import com.tasos.sampleapi.common.dataobjects.FilmDTO;
import com.tasos.sampleapi.common.dataobjects.RentalDTO;
import com.tasos.sampleapi.common.dataobjects.RentalListDTO;
import com.tasos.sampleapi.common.dataobjects.RentalResultDTO;
import com.tasos.sampleapi.common.dataobjects.ReturnFilmsDTO;
import com.tasos.sampleapi.common.dataobjects.ReturnFilmsResultDTO;
import com.tasos.common.enums.FilmType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VRSClientTestingConfig.class)
@TestPropertySource(properties ={ "spring.profiles.active=dev", "vrs.server.host=localhost",
        "vrs.server.port=9081" })
@ActiveProfiles(profiles = { "dev" })
public class VRSApiTest {

    private Logger logger = (Logger) LoggerFactory.getLogger(VRSApiTest.class);

    @Autowired
    VideoRentalStoreClient vrsClient;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    CustomerDTO customer1;

    FilmDTO movie1;
    FilmDTO movie2;
    FilmDTO movie3;

    RentalDTO rental1;
    RentalDTO rental2;
    RentalDTO rental3;

    @Before
    public void setup() {

        customer1 = new CustomerDTO();
        customer1.setBonusPoints(10);

        movie1 = new FilmDTO();
        movie1.setType(FilmType.newRelease.name());

        movie2 = new FilmDTO();
        movie2.setType(FilmType.regularFilm.name());

        movie3 = new FilmDTO();
        movie3.setType(FilmType.oldFilm.name());
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCreateCustomerDTO() {
        CustomerDTO savedCustomer = vrsClient.createCustomer(customer1);
        assertEquals(10, savedCustomer.getBonusPoints().intValue());
        assertNotEquals(null, savedCustomer.getId());
    }

    @Test
    public void testUpdateCustomerDTO() {
        CustomerDTO savedCustomer = vrsClient.createCustomer(customer1);
        savedCustomer.setBonusPoints(20);
        CustomerDTO updatedCustomer = vrsClient.updateCustomer(savedCustomer);
        assertEquals(20, updatedCustomer.getBonusPoints().intValue());
        assertNotEquals(null, updatedCustomer.getId());
    }

    @Test
    public void testGetCustomerById() {
        CustomerDTO savedCustomer = vrsClient.createCustomer(customer1);

        CustomerDTO foundCustomer = vrsClient.findCustomer(savedCustomer);
        assertEquals(savedCustomer.getId(), foundCustomer.getId());
    }

    @Test
    public void testCreateFilmDTO() {
        FilmDTO savedFilm = vrsClient.createFilm(movie1);
        assertEquals(FilmType.newRelease.name(), savedFilm.getType());
        assertNotEquals(null, savedFilm.getId());
    }

    @Test
    public void testUpdateFilmDTO() {
        FilmDTO savedFilm = vrsClient.createFilm(movie1);
        savedFilm.setType(FilmType.regularFilm.name());
        FilmDTO updatedFilm = vrsClient.updateFilm(savedFilm);
        assertEquals(FilmType.regularFilm.name(), updatedFilm.getType());
        assertNotEquals(null, updatedFilm.getId());
    }

    @Test
    public void testGetFilmById() {
        FilmDTO savedFilm = vrsClient.createFilm(movie1);

        FilmDTO foundFilm = vrsClient.findFilm(savedFilm);
        assertEquals(savedFilm.getId(), foundFilm.getId());
    }

    @Test
    public void testRentingFilm() {
        List<RentalDTO> manyRentals = new ArrayList<RentalDTO>();
        CustomerDTO savedCustomer = vrsClient.createCustomer(customer1);
        FilmDTO savedFilm = vrsClient.createFilm(movie1);
        Instant dateRented = Instant.now();
        rental1 = new RentalDTO();
        rental1.setCustomerId(savedCustomer.getId());
        rental1.setFilmId(savedFilm.getId());
        rental1.setDateRented(dateRented);
        manyRentals.add(rental1);
        RentalListDTO rentalListDTO = new RentalListDTO();
        rentalListDTO.setRentalList(manyRentals);
        RentalResultDTO rentalResult = vrsClient.rentFilms(rentalListDTO);
        double expectedInitialCharge = 40;

        assertEquals(expectedInitialCharge, rentalResult.getInitialRentalCharges(), 0.01);

    }

    @Test
    public void testRentingManyFilms() {
        List<RentalDTO> manyRentals = new ArrayList<RentalDTO>();
        CustomerDTO savedCustomer = vrsClient.createCustomer(customer1);
        FilmDTO savedFilm = vrsClient.createFilm(movie1);
        Instant dateRented = Instant.now();
        rental1 = new RentalDTO();
        rental1.setCustomerId(savedCustomer.getId());
        rental1.setFilmId(savedFilm.getId());
        rental1.setDateRented(dateRented);
        manyRentals.add(rental1);
        savedFilm = vrsClient.createFilm(movie2);
        rental2 = new RentalDTO();
        rental2.setCustomerId(savedCustomer.getId());
        rental2.setFilmId(savedFilm.getId());
        rental2.setDateRented(dateRented);
        manyRentals.add(rental2);
        savedFilm = vrsClient.createFilm(movie3);
        rental3 = new RentalDTO();
        rental3.setCustomerId(savedCustomer.getId());
        rental3.setFilmId(savedFilm.getId());
        rental3.setDateRented(dateRented);
        manyRentals.add(rental3);
        RentalListDTO rentalListDTO = new RentalListDTO();
        rentalListDTO.setRentalList(manyRentals);
        RentalResultDTO rentalResult = vrsClient.rentFilms(rentalListDTO);
        double expectedInitialCharge = 100;
        assertEquals(expectedInitialCharge, rentalResult.getInitialRentalCharges(), 0.01);
        CustomerDTO bonusCustomer = vrsClient.findCustomer(savedCustomer);
        assertEquals(14, bonusCustomer.getBonusPoints().intValue());

    }

    @Test
    public void testReturningFilmOnTime() {
        List<RentalDTO> manyRentals = new ArrayList<RentalDTO>();
        CustomerDTO savedCustomer = vrsClient.createCustomer(customer1);
        FilmDTO savedFilm = vrsClient.createFilm(movie2);
        rental1 = new RentalDTO();
        rental1.setCustomerId(savedCustomer.getId());
        rental1.setFilmId(savedFilm.getId());
        rental1.setDateRented(LocalDateTime.now().minusDays(3).toInstant(ZoneOffset.UTC));
        manyRentals.add(rental1);
        RentalListDTO rentalListDTO = new RentalListDTO();
        rentalListDTO.setRentalList(manyRentals);
        vrsClient.rentFilms(rentalListDTO);

        Set<Integer> filmsToReturn = new HashSet<Integer>();
        filmsToReturn.add(rental1.getFilmId());
        Instant dateReturned = Instant.now();
        ReturnFilmsDTO returnFilmsDTO = new ReturnFilmsDTO();

        returnFilmsDTO.setCustomerId(rental1.getCustomerId());
        returnFilmsDTO.setReturnDate(dateReturned);
        returnFilmsDTO.setReturnedFilmIds(filmsToReturn);

        ReturnFilmsResultDTO returnResult = vrsClient.returnFilms(returnFilmsDTO);

//        Duration duration = Duration.between(rental1.getDateRented(), dateReturned);
//        ChronoUnit.DAYS.between(rental1.getDateRented(),dateReturned);
        int daysRented = (int)ChronoUnit.DAYS.between(rental1.getDateRented(),dateReturned);
        assertEquals(3, daysRented);
        double expectedSurcharges = 0.0;
        assertEquals(expectedSurcharges, returnResult.getSurcharges(), 0.01);
    }

    @Test
    public void testReturningNewReleaseLate() {
        int setTestDaysRented = 6;
        CustomerDTO savedCustomer = vrsClient.createCustomer(customer1);
        FilmDTO savedFilm = vrsClient.createFilm(movie1);
        rental1 = new RentalDTO();
        rental1.setCustomerId(savedCustomer.getId());
        rental1.setFilmId(savedFilm.getId());
        rental1.setDateRented(LocalDateTime.now().minusDays(setTestDaysRented).toInstant(ZoneOffset.UTC));
        List<RentalDTO> manyRentals = new ArrayList<RentalDTO>();
        manyRentals.add(rental1);
        RentalListDTO rentalListDTO = new RentalListDTO();
        rentalListDTO.setRentalList(manyRentals);
        vrsClient.rentFilms(rentalListDTO);

        Set<Integer> filmsToReturn = new HashSet<Integer>();
        filmsToReturn.add(rental1.getFilmId());
        Instant dateReturned = Instant.now();
        ReturnFilmsDTO returnFilmsDTO = new ReturnFilmsDTO();

        returnFilmsDTO.setCustomerId(rental1.getCustomerId());
        returnFilmsDTO.setReturnDate(dateReturned);
        returnFilmsDTO.setReturnedFilmIds(filmsToReturn);
        ReturnFilmsResultDTO returnResult = vrsClient.returnFilms(returnFilmsDTO);
        double expectedSurcharges = 40 * (setTestDaysRented - 1);

        assertEquals(expectedSurcharges, returnResult.getSurcharges(), 0.01);
    }

}
