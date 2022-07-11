package edu.kit.tm.cm.smartcampus.buildingmanagement;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.FavoriteRepository;
import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception.RestClientErrorHandler;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Favorite;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * This class represents the building management application microservice application. It holds the
 * main class and runs the spring application.
 */
@SpringBootApplication
public class BuildingManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(BuildingManagementApplication.class, args);
  }

  @Bean
  public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.errorHandler(new RestClientErrorHandler()).build();
  }
}
