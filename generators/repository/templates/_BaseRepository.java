package <%= packageName %>.repository;

import java.util.List;

public interface BaseRepository<T> {

  /**
   * Save new or changement.
   * 
   * @param T entity
   * @return T
   */
  public T save(T entity);

  /**
   * Return all entities.
   * 
   * @return List<T>
   */
  public List<T> findAll();

  /**
   * Find entity by id.
   * 
   * @param Long id
   * @return T
   */
  public T findById(Long id);

  /**
   * Return all entities with pagination.
   * 
   * @param int offset
   * @param int max
   * @return List<T>
   */
  public List<T> findAll(int offset, int max);

  /**
   * Return the number of entities.
   * 
   * @return
   */
  public Long count();

}
