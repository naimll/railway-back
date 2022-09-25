package com.ticketService.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<Offer> getOffers(){

        return offerRepository.findAll();
    }

    public void addNewOffer(Offer offer){

        if (offerRepository.findByOfferType(offer.getOfferType()).isPresent() &&
                offerRepository.findByAmount(offer.getAmount()).isPresent()){

            throw new IllegalStateException("offer exists");
        }
        offerRepository.save(offer);
    }

    public void deleteOffer(Long offerId) {
        offerRepository.deleteById(offerId);
    }

    @Transactional
    public void updateOffer(Long offerId, Offer updatedOffer) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalStateException(
                        "offer with id " + offerId + " does not exist!"
                ));

        if(updatedOffer.getOfferType() != null && !Objects.equals(offer.getOfferType(), updatedOffer.getOfferType())){
            offer.setOfferType(updatedOffer.getOfferType());
        }
        if(updatedOffer.getAmount() !=0 && !Objects.equals(offer.getAmount(), updatedOffer.getAmount())){
            offer.setAmount(updatedOffer.getAmount());
        }
        if(updatedOffer.getValidity() !=null && !Objects.equals(offer.getValidity(), updatedOffer.getValidity())){
            offer.setValidity(updatedOffer.getValidity());
        }


    }
}
