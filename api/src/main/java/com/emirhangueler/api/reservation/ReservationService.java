package com.emirhangueler.api.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emirhangueler.api.Sender;

@Service
public final class ReservationService {
    @Autowired
    private Sender sender;

    public void makeReservation(final String message) {
        this.sender.send(message);
    }
}
