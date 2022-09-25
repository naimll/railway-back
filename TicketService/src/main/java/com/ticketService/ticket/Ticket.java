package com.ticketService.ticket;

import com.ticketService.offer.Offer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity



public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    private String type;

    private Long seatNr;
    /*seatNr = seatRepository.selectSeat();*/

    private double price;

    @ManyToOne
    @JoinColumn(name="offerId", referencedColumnName = "id")
    private Offer offerId;

   /* @OneToOne(mappedBy = "ticketId")
    private Seat seatId;*/


    public Ticket (String type, Long seatNr,double price,Offer offerId){
        this.type=type;
        this.seatNr=seatNr;
        this.price=price;
        this.offerId=offerId;

    }




}
