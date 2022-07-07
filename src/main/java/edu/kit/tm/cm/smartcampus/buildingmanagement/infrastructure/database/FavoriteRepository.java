package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/** This interface represents a favorite repository. */
public interface FavoriteRepository extends JpaRepository<Favorite, String> {}
