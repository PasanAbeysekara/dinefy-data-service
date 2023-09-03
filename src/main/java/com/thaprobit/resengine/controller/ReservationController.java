package com.thaprobit.resengine.controller;

import com.thaprobit.resengine.controller.service.ReservationService;
import com.thaprobit.resengine.dao.Reservation;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tharindu Aththanayake
 * @since 01/09/2021 01:38 AM
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(URLProvider.SERVICE_RESERVATION)
public class ReservationController {
    final ReservationService reservationService;

    /**
     * Get all reservations
     *
     * @param pageable The pageable
     * @return The reservations
     */
    @GetMapping("/reservations")
    public ResponseEntity<ResponseWrapper<Page<Reservation>>> getAllReservations(Pageable pageable) {
        return reservationService.getAllReservations(pageable);
    }

    /**
     * Get Single property
     *
     * @param id property ID
     * @return The PropertyModel
     */
    @GetMapping("/reservations/{id}")
    public ResponseEntity<ResponseWrapper<Reservation>> getReservation(@PathVariable("id") long id) {
        return reservationService.getReservation(id);
    }

    /**
     * Create a reservation
     *
     * @param reservation The reservation
     * @return The saved reservation
     */
    @PostMapping("/reservations")
    public ResponseEntity<ResponseWrapper<Reservation>> createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    /**
     * Update a reservation
     *
     * @param id          The reservation ID
     * @param reservation The reservation
     * @return The updated reservation
     */
    @PutMapping("/reservations/{id}")
    public ResponseEntity<ResponseWrapper<Reservation>> updateReservation(@PathVariable("id") long id, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    /**
     * Delete a reservation
     *
     * @param id The reservation ID
     * @return The deleted reservation response
     */
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<ResponseWrapper<Reservation>> deleteReservation(@PathVariable("id") long id) {
        return reservationService.deleteReservation(id);
    }
}
