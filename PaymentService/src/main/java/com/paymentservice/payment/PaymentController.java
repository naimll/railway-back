package com.paymentservice.payment;

import com.paymentservice.dtos.CreatePayment;
import com.paymentservice.dtos.CreatePaymentResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PaymentController {

    @PostMapping("/create-payment-intent")
    public CreatePaymentResponse createPaymentIntent(@RequestBody @Valid CreatePayment createPayment) throws StripeException {
        CustomerCreateParams customerParams = new CustomerCreateParams.Builder().build();
        Customer customer = Customer.create(customerParams);

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setCustomer(customer.getId())
                        .setSetupFutureUsage(PaymentIntentCreateParams.SetupFutureUsage.OFF_SESSION)
                        .setAmount(createPayment.getAmount() * 100L)
                        .setCurrency("eur")
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();
        // Create a PaymentIntent with the order amount and currency
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return new CreatePaymentResponse(paymentIntent.getClientSecret());
    }


}
