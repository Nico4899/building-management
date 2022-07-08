package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.PrefixSequenceGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/** This class represents a favorite entity, which is used to store favorite data. */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = Favorite.FAVORITE_ENTITY)
public class Favorite {

  private static final String IDENTIFICATION_NUMBER_COLUMN_NAME = "identification_number";
  private static final String REFERENCE_IDENTIFICATION_NUMBER_COLUMN_NAME =
      "reference_identification_number";
  private static final String OWNER_COLUMN_NAME = "owner";
  private static final String FAVORITE_SEQUENCE = "favorite_sequence";
  private static final String TEXT = "TEXT";
  private static final String FAVORITE_PREFIX = "f-";
  private static final String PREFIX_GENERATOR_URL =
      "edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.PrefixSequenceGenerator";
  protected static final String FAVORITE_ENTITY = "Favorite";

  public Favorite(String owner, String referenceIdentificationNumber) {
    this.owner = owner;
    this.referenceIdentificationNumber = referenceIdentificationNumber;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = FAVORITE_SEQUENCE)
  @SequenceGenerator(name = FAVORITE_SEQUENCE, allocationSize = 1)
  @GenericGenerator(
      name = FAVORITE_SEQUENCE,
      strategy = PREFIX_GENERATOR_URL,
      parameters = {
        @Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER, value = FAVORITE_PREFIX)
      })
  @Column(
      name = IDENTIFICATION_NUMBER_COLUMN_NAME,
      nullable = false,
      updatable = false,
      columnDefinition = "TEXT")
  private String identificationNumber;

  @Column(name = OWNER_COLUMN_NAME, nullable = false, updatable = false, columnDefinition = TEXT)
  private String owner;

  @Column(
      name = REFERENCE_IDENTIFICATION_NUMBER_COLUMN_NAME,
      nullable = false,
      columnDefinition = TEXT)
  private String referenceIdentificationNumber;
}
