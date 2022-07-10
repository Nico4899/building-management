package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.PrefixSequenceGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * This class represents a favorite entity, which is used to store favorite data. It contains of its
 * own identification number as well as a reference identification number representing the
 * identification of it's referenced entity (e.g., {@link Building}, {@link Room} or {@link
 * Component}. Its owner represents an email value, which represents the person to which this
 * favorite belongs.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Favorite {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_sequence")
  @SequenceGenerator(name = "favorite_sequence", allocationSize = 1)
  @GenericGenerator(
      name = "favorite_sequence",
      strategy =
          "edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.PrefixSequenceGenerator",
      parameters = {
        @Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "f-")
      })
  private String identificationNumber;

  private String owner;
  private String referenceIdentificationNumber;

  public Favorite(String owner, String referenceIdentificationNumber) {
    this.owner = owner;
    this.referenceIdentificationNumber = referenceIdentificationNumber;
  }
}
