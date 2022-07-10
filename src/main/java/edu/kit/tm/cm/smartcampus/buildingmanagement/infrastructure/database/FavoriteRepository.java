package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Favorite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/** This interface represents a favorite repository. */
public interface FavoriteRepository extends CrudRepository<Favorite, String> {

  @Query("SELECT '*' FROM favorite WHERE owner = 'owner'")
  Collection<Favorite> findByOwner(@Param("owner") String owner);
}
