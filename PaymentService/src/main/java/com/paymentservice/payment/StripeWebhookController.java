package com.paymentservice.payment;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class StripeWebhookController {
    private Logger logger = LoggerFactory.getLogger(StripeWebhookController.class);
    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    private PaymentService paymentService;

    @Autowired
    public StripeWebhookController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/stripe/events")
    public String handleStripeEvent(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader){
        if(sigHeader == null){
            return "";
        }

        Event event;
        // Only verify the event if you have an endpoint secret defined.
        // Otherwise use the basic event deserialized with GSON.
        try {
            event = Webhook.constructEvent(
                    payload, sigHeader, endpointSecret
            );
        } catch (SignatureVerificationException e) {
            // Invalid signature
            logger.info("⚠️  Webhook error while validating signature.");
            return "";
        }
        logger.info(event.toJson());
        // Deserialize the nested object inside the event
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
            logger.info("failed");
        }
        // Handle the event
        switch (event.getType()) {
            case "payment_intent.succeeded":
                PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                assert paymentIntent != null;
                logger.info("Payment for {} succeeded ", paymentIntent.getAmount());
                // Then define and call a method to handle the successful payment intent.
                handlePaymentIntentSucceeded(paymentIntent);
                break;
            default:
                logger.warn("Unhandled event type: {}", event.getType());
                break;
        }
        return "";
    }

    private void handlePaymentIntentSucceeded(PaymentIntent paymentIntent) {
        Payment p = new Payment();

        p.setPayment_intent_id(paymentIntent.getId());
        p.setAmount((paymentIntent.getAmount()).intValue());
        p.setPayTime(LocalDateTime.now());
        p.setEmail(paymentIntent.getReceiptEmail());
        p.setCustomer_id(paymentIntent.getCustomer());
        paymentService.addNewPayment(p);
    }
}
