package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database;

import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Favorite;
import org.springframework.data.repository.CrudRepository;

public interface FavoriteRepository extends CrudRepository<Favorite, String> {
}
