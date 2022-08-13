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
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class Favorite {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_sequence")
  @SequenceGenerator(name = "favorite_sequence", allocationSize = 1)
  @GenericGenerator(
      name = "favorite_sequence",
      strategy =
          "edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.generator.PrefixSequenceGenerator",
      parameters = {
        @Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "f-")
      })
  private String identificationNumber;

  @Column(
      nullable = false,
      updatable = false,
      columnDefinition =
          "varchar(255) constraint match_email_regex CHECK (OWNER ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$')")
  private String owner;

  @Column(
      nullable = false,
      updatable = false,
      columnDefinition =
          "varchar(255) constraint match_id_regex CHECK (REFERENCE_IDENTIFICATION_NUMBER ~* '^(b|c|r)-[1-9]+$')")
  private String referenceIdentificationNumber;
}
