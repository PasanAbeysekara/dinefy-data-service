package com.thaprobit.resengine.controller;

import com.thaprobit.resengine.controller.service.ReservationService;
import com.thaprobit.resengine.dao.Reservation;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(URLProvider.SERVICE_RESERVATION)
public class ReservationController {
    final ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity<ResponseWrapper<Page<Reservation>>> getAllReservations(Pageable pageable) {
        return reservationService.getAllReservations(pageable);
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<ResponseWrapper<Reservation>> getReservation(@PathVariable("id") long id) {
        return reservationService.getReservation(id);
    }

    @GetMapping("/reservations/code/{reserveCode}")
    public ResponseEntity<ResponseWrapper<Reservation>> getReservationByCode(@PathVariable("reserveCode") String reserveCode) {
        return reservationService.getReservationByCode(reserveCode);
    }

    @PostMapping("/reservations")
    public ResponseEntity<ResponseWrapper<Reservation>> createReservation(@AuthenticationPrincipal Long userId, @RequestBody Reservation reservation) {
        reservation.setUserId(userId);
        return reservationService.createReservation(reservation);
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity<ResponseWrapper<Reservation>> updateReservation(@PathVariable("id") long id, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<ResponseWrapper<Reservation>> deleteReservation(@PathVariable("id") long id) {
        return reservationService.deleteReservation(id);
    }
}
