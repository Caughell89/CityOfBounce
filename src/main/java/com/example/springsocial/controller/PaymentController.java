package com.example.springsocial.controller;

import com.example.springsocial.model.Location;
import com.example.springsocial.model.PartyAddress;
import com.example.springsocial.model.TaxRate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;
import com.stripe.model.PaymentIntent;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountLinkCreateParams;
import static io.jsonwebtoken.Jwts.header;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PaymentController {

    private static Gson gson = new Gson();

    private static final ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String processPayment(@RequestBody int price) throws StripeException {
        Stripe.apiKey = "sk_test_gXlNSy1TSr8wEhlc2eDIzyAl00uPeaMRms";
        System.out.println(price);
        double fees = (price * .029 + 100)/100;
        System.out.println(fees);
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
//        params.put("application_fee_amount", fees);


        PaymentIntent paymentIntent
                = PaymentIntent.create(params);
        return gson.toJson(paymentIntent);

    }

    @RequestMapping(value = "/payment/register", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String processRegestration() throws StripeException {
        Stripe.apiKey = "sk_test_gXlNSy1TSr8wEhlc2eDIzyAl00uPeaMRms";

        AccountCreateParams params
                = AccountCreateParams.builder()
                        .setType(AccountCreateParams.Type.STANDARD)
                        .build();

        Account account = Account.create(params);

        AccountLinkCreateParams linkParams
                = AccountLinkCreateParams.builder()
                        .setAccount(account.getId())
                        .setRefreshUrl("http://localhost:3000/reauth")
                        .setReturnUrl("http://localhost:3000/companyInfo")
                        .setType(AccountLinkCreateParams.Type.ACCOUNT_ONBOARDING)
                        .build();

        AccountLink accountLink = AccountLink.create(linkParams);
        String accountLinkUrl = accountLink.getUrl();
        System.out.println(accountLinkUrl);
        return gson.toJson(accountLinkUrl);

    }
    
    @CrossOrigin
    @RequestMapping(value = "/resource/taxes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TaxRate getTaxRates(@RequestBody PartyAddress address) throws IOException {

        String url1 = "https://rest.avatax.com/api/v2/taxrates/byaddress?line1=" + address.getLine1().replaceAll(" ", "%20") + "&city=" + address.getCity().replaceAll(" ", "%20") + "&region=" + address.getState() + "&postalCode=" + address.getZip() + "&country=" + address.getCountry() + "";
        String userCredentials = "2000166321:B17630A07D068DF2";
        String cred = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url1);
            request.addHeader("Authorization", cred);
            System.out.println("Looking at the headers");
            for (int i = 0; i < request.getAllHeaders().length; i++) {
                System.out.println(request.getAllHeaders()[i]);
            }
            System.out.println(request);
            TaxRate response = client.execute(request, httpResponse
                    -> mapper.readValue(httpResponse.getEntity().getContent(), TaxRate.class));

            try (CloseableHttpResponse response1 = client.execute(request)) {
                StatusLine statusLine = response1.getStatusLine();
                System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
                String responseBody = EntityUtils.toString(response1.getEntity(), StandardCharsets.UTF_8);
                System.out.println("Response body: " + responseBody);
                System.out.println(response1.getEntity());
            }

            System.out.println(response.totalRate);

            return response;
        }

    }

}
