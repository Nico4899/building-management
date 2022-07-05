package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.PrefixSequenceGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity(name = "Favorite")
public class Favorite {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_sequence")
  @GenericGenerator(
    name = "favorite_sequence",
    strategy = "edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.PrefixSequenceGenerator",
    parameters = {@Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "B_")})
  @Column(name = "identification_number", updatable = false, columnDefinition = "TEXT")
  private String identificationNumber;

  @Column(name = "owner", nullable = false, updatable = false, columnDefinition = "TEXT")
  private String owner;

  @Column(name = "reference_identification_number", nullable = false, columnDefinition = "TEXT")
  private String referenceIdentificationNumber;
}
