package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.repository.favorite;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * This interface represents a favorite repository.
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, String> {

  /**
   * Obtain all favorites where owner matches and regex of reference number matches.
   *
   * @param owner owner of the favorite
   * @param regex regex of bin, cin, rin
   * @return Collection of favorites
   */
  @Query(
      "SELECT favorite FROM favorite favorite WHERE (favorite.owner = :#{#owner}) AND (favorite.referenceIdentificationNumber LIKE :#{#regex})")
  Collection<Favorite> findByOwnerAndRegex(
      @Param("owner") String owner, @Param("regex") String regex);
}
