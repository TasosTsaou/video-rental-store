package com.tasos.sampleapi.server.sampleapi;

import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

import com.tasos.sampleapi.common.dataobjects.RentalDTO;
import com.tasos.sampleapi.common.dataobjects.RentalResultDTO;
import com.tasos.sampleapi.common.dataobjects.ReturnFilmsDTO;
import com.tasos.sampleapi.common.dataobjects.ReturnFilmsResultDTO;

/**
 * @author tasos
 */
public interface RentalManagementService {

    RentalDTO createRental(RentalDTO rentalDTO);

    RentalDTO updateRental(RentalDTO rentalDTO);

    RentalDTO getRentalById(int RentalId);

    List<RentalDTO> getRentalsByFilmId(int filmId);

    List<RentalDTO> getRentalsByCustomerId(int customerId);

    Double calculateRentalPrice(int rentalId);

    Integer awardBonusPoints(int rentalId);

    RentalResultDTO rentFilms(List<RentalDTO> filmsList);

    ReturnFilmsResultDTO returnFilms(Set<Integer> returnedFilmIds, int customerId, DateTime returnDate);

}
