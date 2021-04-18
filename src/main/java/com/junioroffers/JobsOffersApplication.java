package com.junioroffers;

import com.junioroffers.infrastructure.offer.client.OfferClient;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JobsOffersApplication {

    private static final Logger log =
            LoggerFactory.getLogger(JobsOffersApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JobsOffersApplication.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception{
        return args -> {
            OfferClient offerClient = restTemplate.getForObject("https://nofluffjobs.com/pl/jobs/java?criteria=seniority%3Djunior", OfferClient.class);
            log.info(offerClient.toString());
        };
    }
}