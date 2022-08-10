package edu.kit.tm.cm.smartcampus.buildingmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point of the Application. This application ist a microservice implementation of
 * the microservice "building-management" it provides logical operations on "building" domain
 * objects, retrieved from to connected domain microservices.
 */
@SpringBootApplication
public class Application {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
