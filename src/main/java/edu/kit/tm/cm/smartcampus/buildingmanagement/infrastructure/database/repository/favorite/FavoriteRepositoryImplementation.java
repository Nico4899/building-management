package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database.repository.favorite;

import edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.service.error.exceptions.ResourceNotFoundException;
import edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.Favorite;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class FavoriteRepositoryImplementation implements FavoriteRepository {

  private final FavoriteRepository favoriteRepository;

  @Autowired
  public FavoriteRepositoryImplementation(FavoriteRepository favoriteRepository) {
    this.favoriteRepository = favoriteRepository;
  }

  @NonNull
  @Override
  public Collection<Favorite> findByOwnerAndRegex(@NonNull String owner, @NonNull String regex) {
    return this.favoriteRepository.findByOwnerAndRegex(owner, regex);
  }

  @NonNull
  @Override
  public <S extends Favorite> S save(@NonNull S entity) {
    try {
      return this.favoriteRepository.save(entity);
    } catch (Exception exception) {
      throw new IllegalArgumentException();
    }
  }

  @NonNull
  @Override
  public <S extends Favorite> Iterable<S> saveAll(@NonNull Iterable<S> entities) {
    return this.favoriteRepository.saveAll(entities);
  }

  @NonNull
  @Override
  public Optional<Favorite> findById(@NonNull String s) {
    return this.favoriteRepository.findById(s);
  }

  @Override
  public boolean existsById(@NonNull String s) {
    return this.favoriteRepository.existsById(s);
  }

  @NonNull
  @Override
  public Iterable<Favorite> findAll() {
    return this.favoriteRepository.findAll();
  }

  @NonNull
  @Override
  public Iterable<Favorite> findAllById(@NonNull Iterable<String> strings) {
    return this.favoriteRepository.findAllById(strings);
  }

  @Override
  public long count() {
    return this.favoriteRepository.count();
  }

  @Override
  public void deleteById(@NonNull String s) {
    if (!existsById(s))
      throw new ResourceNotFoundException(
          String.format(ResourceNotFoundException.RESOURCE_NOT_FOUND_MESSAGE, s));
    this.favoriteRepository.deleteById(s);
  }

  @Override
  public void delete(@NonNull Favorite entity) {
    this.favoriteRepository.delete(entity);
  }

  @Override
  public void deleteAllById(@NonNull Iterable<? extends String> strings) {
    this.favoriteRepository.deleteAllById(strings);
  }

  @Override
  public void deleteAll(@NonNull Iterable<? extends Favorite> entities) {
    this.favoriteRepository.deleteAll(entities);
  }

  @Override
  public void deleteAll() {
    this.favoriteRepository.deleteAll();
  }
}
