package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Favorite")
@Data
public class
Favorite {
  @Id
  @SequenceGenerator(
          name = "favorite_sequence",
          sequenceName = "favorite_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = SEQUENCE,
          generator = "favorite_sequence"
  )

  @Column(
          name= "identification_number",
          updatable = false,
          columnDefinition = "TEXT"

  )
  private String identificationNumber;
  @Column(
          name = "owner",
          nullable = false,
          updatable = false,
          columnDefinition = "TEXT"
  )
  private String owner;
  @Column(
          name = "reference_identification_number",
          nullable = false,
          columnDefinition = "TEXT"
  )
  private String referenceIdentificationNumber;
}
