package com.emirhangueler.api.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public final class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public void makeReservation(@RequestBody final String message) {
        this.reservationService.makeReservation(message);
    }

}
