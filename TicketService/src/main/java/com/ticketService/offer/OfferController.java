package com.ticketService.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/offers")

public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {

        this.offerService = offerService;
    }

    @GetMapping
    public List<Offer> getOffers() {

        return offerService.getOffers();
    }
    @PostMapping
    public void createNewOffer(@RequestBody Offer offer){
        offerService.addNewOffer(offer);
    }

    @DeleteMapping(path = "{offerId}")
    public void deleteOffer(@PathVariable("offerId") Long offerId){
        offerService.deleteOffer(offerId);
    }

    @PutMapping(path = "{offerId}")
    public void updateOffer(
            @PathVariable("offerId") Long offerId,
            @RequestBody Offer updatedOffer
    ){
        offerService.updateOffer(offerId, updatedOffer);
    }

}
