package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.repository.favorite;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Favorite;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * This interface represents a favorite repository.
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, String> {

  String BIN_SQL_PATTERN = "^b-%$";
  String RIN_SQL_PATTERN = "^r-%$";
  String CIN_SQL_PATTERN = "^c-%$";

  /**
   * Obtain all favorites where owner matches and regex of reference number matches.
   *
   * @param owner owner of the favorite
   * @param regex regex of bin, cin, rin
   * @return Collection of favorites
   */
  @NonNull
  @Query(
      "SELECT favorite FROM Favorite favorite WHERE (favorite.owner = :#{#owner}) AND (favorite.referenceIdentificationNumber LIKE :#{#regex})")
  Collection<Favorite> findByOwnerAndRegex(
      @NonNull @Param("owner") String owner, @NonNull @Param("regex") String regex);
}
