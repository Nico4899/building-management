package edu.kit.tm.cm.smartcampus.buildingmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BuildingManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(BuildingManagementApplication.class, args);
  }

  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
