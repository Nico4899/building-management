package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.generator.PrefixSequenceGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * This class represents a favorite entity, which is used to store favorite data. It contains of its
 * own identification number as well as a reference identification number representing the
 * identification of the referred entity (e.g., {@link Building}, {@link Room} or {@link
 * Component}). Its owner represents an email value, which represents the person to which this
 * favorite belongs.
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name = Favorite.FAVORITE_ENTITY)
@Table
public class Favorite {

  // must be public since the {@Entity} annotation can't read it if its private
  public static final String FAVORITE_ENTITY = "favorite";

  private static final String GENERATOR_PATH =
      "edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.generator.PrefixSequenceGenerator";
  private static final String FAVORITE_SEQUENCE = "favorite_sequence";
  private static final String IDENTIFICATION_NUMBER = "identification_number";
  private static final String REFERENCE_IDENTIFICATION_NUMBER = "reference_identification_number";
  private static final String PREFIX = "f-";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = FAVORITE_SEQUENCE, allocationSize = 1)
  @GenericGenerator(
      name = FAVORITE_SEQUENCE,
      strategy = GENERATOR_PATH,
      parameters = {
        @Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER, value = PREFIX)
      })
  @Column(name = IDENTIFICATION_NUMBER)
  private String identificationNumber;

  private String owner;

  @Column(name = REFERENCE_IDENTIFICATION_NUMBER)
  private String referenceIdentificationNumber;
}
