package <%=packageName%>.service;

import java.util.List;

public interface BaseService<T> {

  /**
   * Save/Update a DTO.
   * 
   * @param T dto
   * @return T
   */
  public T save (T dto);

  /**
   * Return DTO by Id.
   * 
   * @param id
   * @return T
   */
  public T getById(Long id);

  /**
   * Return all DTO's
   * 
   * @return List<T>
   */
  public List<T> getAll();

  /**
   * Return all DTO's for pagination.
   * 
   * @param Integer offset
   * @param Integer max
   * @return List<T>
   */
  public List<T> getAll(Integer offset, Integer max);

  /**
   * Return the numbers of DTO's.
   * 
   * @return Long
   */
  public Long count();
}