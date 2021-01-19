package com.mps.reserveme.service;

import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class Scheduler {

    @Scheduled(fixedRate = 60000)
    public void create() {
       System.out.println("SAL");
       /*
        tinem rezervarile in memorie si le updatam la fiecare add/update/delete de reservations  -> getAllReservations()
        luam intervalele de timp pentru fiecare rezervare si comparam cu timpul curent
        in functie de asta, updatam starea resursei respective


       */

    }
}
