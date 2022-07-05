package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.PrefixSequenceGenerator;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity(name = "Favorite")
@Setter
@Getter
@NoArgsConstructor
public class Favorite {

  public Favorite(String owner, String referenceIdentificationNumber) {
    this.owner = owner;
    this.referenceIdentificationNumber = referenceIdentificationNumber;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_sequence")
  @GenericGenerator(
    name = "favorite_sequence",
    strategy = "edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.PrefixSequenceGenerator",
    parameters = {@Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "f-")})
  @Column(name = "identification_number", updatable = false, columnDefinition = "TEXT")
  private String identificationNumber;

  @Column(name = "owner", nullable = false, updatable = false, columnDefinition = "TEXT")
  private String owner;

  @Column(name = "reference_identification_number", nullable = false, columnDefinition = "TEXT")
  private String referenceIdentificationNumber;
}
