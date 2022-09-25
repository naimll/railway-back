package com.ticketService.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Repository
@Service
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Object> findByOfferType(String offerType);

    Optional<Object> findByAmount(double amount);
}
