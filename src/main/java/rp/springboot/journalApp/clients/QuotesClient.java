package rp.springboot.journalApp.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rp.springboot.journalApp.clients.response.QuoteResponses;
import rp.springboot.journalApp.clients.response.QuotesResponse;

import java.util.Arrays;
import java.util.List;


@Service
public class QuotesClient {

    private static final String apiKey = "t6YkYT1cr4BcaPjYd/5P3w==LTfuwciCAA0IuSkB";
    private static final String apiUrl = "https://api.api-ninjas.com/v1/quotes?category=CATEGORY";

    @Autowired
    private RestTemplate restTemplate; //Class in spring boot which processes the HTTP requests

    public QuoteResponses getQuotesUrl(String category){
        // Create HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key",apiKey);

        // Create HttpEntity with HttpHeaders
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url= apiUrl.replace("CATEGORY",category);
        ResponseEntity<QuoteResponses> response = restTemplate.exchange(url, HttpMethod.GET, entity, QuoteResponses.class);
        return response.getBody();
    }

}
