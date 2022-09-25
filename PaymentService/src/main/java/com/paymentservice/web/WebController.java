package com.paymentservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class WebController {

    @Value("${stripe.public.key}")
    private String stripePublicKey;

    @GetMapping("/payment")
    public String home(Model model){
        model.addAttribute("checkOutForm", new CheckOutForm());
        return "index";
    }

    @GetMapping("/success")
    public String success(Model model){
        model.addAttribute("stripePublicKey", stripePublicKey);
        return "ok";
    }

    @PostMapping("/payment")
    public String checkout(@ModelAttribute @Valid CheckOutForm checkOutForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "index";
        }
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("amount", checkOutForm.getAmount());
        model.addAttribute("email", checkOutForm.getEmail());
        return "checkout";
    }
}
