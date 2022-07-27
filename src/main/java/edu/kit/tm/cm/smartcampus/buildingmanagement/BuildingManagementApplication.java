package edu.kit.tm.cm.smartcampus.buildingmanagement;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.ClientExceptionInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/** The main entry point of the Application. */
@SpringBootApplication
public class BuildingManagementApplication {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(BuildingManagementApplication.class, args);
  }

  /**
   * The {@link RestTemplate} {@link Bean} used for the connectors
   *
   * @return the rest template
   */
  @Bean
  public RestTemplate getRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new ClientExceptionInterceptor());
    return restTemplate;
  }
}
