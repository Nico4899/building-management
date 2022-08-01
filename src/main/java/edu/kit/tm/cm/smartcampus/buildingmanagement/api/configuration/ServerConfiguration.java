package edu.kit.tm.cm.smartcampus.buildingmanagement.api.configuration;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.error.ClientExceptionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * This class represents a {@link Configuration} for spring, it contains of important application
 * settings and beans used upon the application.
 */
@Configuration
public class ServerConfiguration {

  /**
   * The {@link RestTemplate} {@link Bean} used as tool for the connectors to call and obtain data
   * from connected domain microservices, it is capable of parsing api responses directly to model
   * objects of this microservice, it reduces a lot of own implementation and JSON parsing.
   *
   * @return the rest template used in this service
   */
  @Bean
  public RestTemplate getRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new ClientExceptionInterceptor());
    return restTemplate;
  }
}
