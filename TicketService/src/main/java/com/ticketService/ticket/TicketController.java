package com.ticketService.ticket;

/*import com.travelservice.trainmodule.seat.Seat;
import com.travelservice.trainmodule.seat.SeatService;
import com.travelservice.trainmodule.train.Train;*/
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/tickets")

public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){

        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getTickets(){

        return ticketService.getTickets();
    }

    @PostMapping
    public void createNewTicket (@RequestBody Ticket ticket){

        ticketService.addNewTicket(ticket);
    }

    @DeleteMapping(path = "{ticketId}")
    public void deleteTicket(@PathVariable("ticketId") Long ticketId){

        ticketService.deleteTicket(ticketId);
    }

    @PutMapping(path = "{ticketId}")
    public void updateTicket(
            @PathVariable("ticketId") Long ticketId,
            @RequestBody Ticket updatedTicket
    ){
        ticketService.updateTicket(ticketId, updatedTicket);
    }




}
