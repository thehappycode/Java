package com.thehappycode.actuator.heath.indicator;

import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class DogsApiHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // TODO Auto-generated method stub
        try {
            ParameterizedTypeReference<Map<String, String>> reference 
                = new ParameterizedTypeReference<Map<String,String>>() {};
            ResponseEntity<Map<String, String>> result
                = new RestTemplate().exchange(
                    "https://dog.ceo/api/breeds/image/random",
                    HttpMethod.GET, null, reference
            );
            if(result.getStatusCode().is2xxSuccessful()
                && result.getBody() != null    
            ){
                return Health.up().withDetails(result.getBody()).build();
            }

            return Health.down().withDetail("status", result.getStatusCode()).build();

        } catch (RestClientException ex) {
            // TODO: handle exception
            return Health.down().withException(ex).build();
        }
    }

}
