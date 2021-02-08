package jm.pp_rest_template.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("jm.pp_rest_template")
public class WebConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
