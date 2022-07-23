package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.validator;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.FavoriteRepository;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * This class is a child implementation of the {@link Validator}, it focuses on validating {@link
 * Favorite} requests. It calls parent methods to validate certain attributes.
 */
@Component
public class FavoriteValidator extends Validator<Favorite> {

  /**
   * Instantiates a new Validator.
   *
   * @param favoriteRepository the favorite repository
   */
  @Autowired
  protected FavoriteValidator(FavoriteRepository favoriteRepository) {
    super(favoriteRepository);
  }

  @Override
  protected String getValidateRegex() {
    return FIN_PATTERN;
  }

  @Override
  public void validateCreate(Favorite object) {
    validateNotNull(
        Map.of(
            OWNER_NAME, object.getOwner(),
            REFERENCE_IDENTIFICATION_NUMBER_NAME, object.getReferenceIdentificationNumber()));
    validateMatchesRegex(
        Map.of(
            REFERENCE_IDENTIFICATION_NUMBER_NAME,
            Pair.of(object.getReferenceIdentificationNumber(), BIN_RIN_CIN_PATTERN)));
    validateNotEmpty(Map.of(OWNER_NAME, object.getOwner()));
  }

  @Override
  public void validateUpdate(Favorite object) {
    validateNotNull(
        Map.of(
            OWNER_NAME,
            object.getOwner(),
            REFERENCE_IDENTIFICATION_NUMBER_NAME,
            object.getReferenceIdentificationNumber(),
            IDENTIFICATION_NUMBER_NAME,
            object.getIdentificationNumber()));
    validateMatchesRegex(
        Map.of(
            REFERENCE_IDENTIFICATION_NUMBER_NAME,
                Pair.of(object.getReferenceIdentificationNumber(), BIN_RIN_CIN_PATTERN),
            IDENTIFICATION_NUMBER_NAME, Pair.of(object.getIdentificationNumber(), FIN_PATTERN)));
    validateNotEmpty(Map.of(OWNER_NAME, object.getOwner()));
  }
}
