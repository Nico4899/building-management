package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.connector.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link ProblemConnector}.
 *
 * @author Bastian Bacher
 */
@Service
public class ClientProblemConnector implements ProblemConnector {

  private final String baseUrl;
  private final RestTemplate restTemplate;

  @Value("${problem.removeProblemsByReferenceIdentificationNumber}")
  private String removeProblemsByReferenceIdentificationNumberUrl;

  /**
   * Constructs a new rest template building connector.
   *
   * @param restTemplate rest template
   * @param baseUrl base url
   */
  @Autowired
  public ClientProblemConnector(
      RestTemplate restTemplate, @Value("${problem.baseUrl}") String baseUrl) {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }

  @Override
  public void removeProblemsByReferenceIdentificationNumber(String identificationNumber) {
    restTemplate.exchange(
        baseUrl + removeProblemsByReferenceIdentificationNumberUrl,
        HttpMethod.DELETE,
        HttpEntity.EMPTY,
        Void.class,
        identificationNumber);
  }
}
