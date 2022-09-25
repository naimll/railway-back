package com.paymentservice.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    public void addNewPayment(Payment payment){
        paymentRepository.save(payment);
    }
}
