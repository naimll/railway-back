package com.ticketService.offer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticketService.ticket.Ticket;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "offerId")
    private List<Ticket> ticketIdTickets = new ArrayList<>();


    private String offerType ;
    private double amount;

    private String validity;


    public Offer (List<Ticket> ticketIdTickets ,String offerType, double amount, String validity){

        this.ticketIdTickets=ticketIdTickets;
        this.offerType=offerType;
        this.amount=amount;
        this.validity=validity;


    }


}
