package edu.kit.tm.cm.smartcampus.buildingmanagement;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.FavoriteRepository;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Favorite;
import org.springframework.boot.CommandLineRunner;
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

  @Bean
  CommandLineRunner commandLineRunner(FavoriteRepository favoriteRepository) {
    return args -> {
      Favorite one = new Favorite("ich", "22");
      Favorite two = new Favorite("du", "23");
      favoriteRepository.save(one);
      favoriteRepository.save(two);
    };
  }
}
