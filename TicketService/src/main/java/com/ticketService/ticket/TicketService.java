package com.ticketService.ticket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTickets() {

        return ticketRepository.findAll();
    }


    public void addNewTicket(Ticket ticket){

        if (ticketRepository.findBySeatNr(ticket.getSeatNr()).isPresent()){

            throw new IllegalStateException("ticket exists");
        }
        ticketRepository.save(ticket);
    }

    public void deleteTicket(Long ticketId) {

        ticketRepository.deleteById(ticketId);
    }

   /* public Ticket selectTicket(Long ticketId) {
        return ticketRepository.selectTicket(ticketId);
    }*/

    @Transactional
    public void updateTicket(Long ticketId, Ticket updatedTicket) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalStateException(
                        "ticket with id " + ticketId + " does not exist!"
                ));

        if (updatedTicket.getType() != null  && !Objects.equals(ticket.getType(), updatedTicket.getType())) {
            ticket.setType(updatedTicket.getType());
        }
        if (updatedTicket.getSeatNr() != 0 && !Objects.equals(ticket.getSeatNr(), updatedTicket.getSeatNr())) {
            ticket.setSeatNr(updatedTicket.getSeatNr());
        }
        if (updatedTicket.getPrice() != 0 && !Objects.equals(ticket.getPrice(), updatedTicket.getPrice())) {
            ticket.setPrice(updatedTicket.getPrice());
        }

    }
}
