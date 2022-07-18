package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.PrefixSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Entity(name = "favorite")
@Table
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
  @Column(name = "identification_number")
  private String identificationNumber;

  private String owner;

  @Column(name = "reference_identification_number")
  private String referenceIdentificationNumber;
}
