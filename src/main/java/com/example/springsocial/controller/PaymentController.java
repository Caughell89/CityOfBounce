package com.example.springsocial.controller;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PaymentController {
    private static Gson gson = new Gson();

    

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String processPayment(@RequestBody int price) throws StripeException {
        Stripe.apiKey = "sk_test_gXlNSy1TSr8wEhlc2eDIzyAl00uPeaMRms";

        List<Object> paymentMethodTypes
                = new ArrayList<>();
        paymentMethodTypes.add("card");
        Map<String, Object> params = new HashMap<>();
        params.put("amount", price);
        params.put("currency", "usd");
        params.put(
                "payment_method_types",
                paymentMethodTypes
        );

        PaymentIntent paymentIntent
                = PaymentIntent.create(params);
        return gson.toJson(paymentIntent);
       
    
    }
    

    
}
