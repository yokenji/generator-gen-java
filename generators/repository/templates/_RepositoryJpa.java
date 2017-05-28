package <%= packageName %>.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import <%= packageName %>.repository.<%= repositoryClass %>Repository;

@Repository<% if(implementsBaseRepository == true) { %>
public class <%= repositoryClass %>RepositoryJpa implements <%= repositoryClass %>Repository {
<% } else { %>
public class <%= repositoryClass %>Repository {
<% } %>
  @PersistenceContext
  private EntityManager em;
<% if(implementsBaseRepository == true) { %>
  /* (non-Javadoc)
   * @see <%= packageName %>.repository.BaseRepository#save(java.lang.Object)
   */
  @Override
  public <%= repositoryClass %> save(<%= repositoryClass %> entity) {
    return em.merge(entity);
  }

  /* (non-Javadoc)
   * @see <%= packageName %>.repository.BaseRepository#findAll()
   */
  @Override
  public List<<%= repositoryClass%>> findAll() {
    String queryString = "from <%= repositoryClass %> <%= repositoryClassPrefix %>";
    List<<%= repositoryClass %>> results = em.createQuery(queryString, <%= repositoryClass %>.class)
        .getResultList();
    return results;
  }

  /* (non-Javadoc)
   * @see <%= packageName %>.repository.BaseRepository#findById(java.lang.Long)
   */
  @Override
  public <%= repositoryClass %> findById(Long id) {
    String queryString = "from <%= repositoryClass %> <%= repositoryClassPrefix %> where <%= repositoryClassPrefix %>.id = :id";
    List<<%= repositoryClass %>> results = em.createQuery(queryString, <%= repositoryClass %>.class)
        .setParameter("id", id)
        .getResultList();
    return (!results.isEmpty() ? results.get(0) : null);
  }

  /* (non-Javadoc)
   * @see <%= packageName %>.repository.BaseRepository#findAll(int, int, java.lang.String)
   */
  @Override
  public List<<%= repositoryClass %>> findAll(int offset, int max) {
    String queryString = "from <%= repositoryClass %> <%= repositoryClassPrefix %>";
    List<<%= repositoryClass %>> results = em.createQuery(queryString, <%= repositoryClass %>.class)
        .setFirstResult(offset)
        .setMaxResults(max)
        .getResultList();
    return results;
  }

  /* (non-Javadoc)
   * @see <%= packageName %>.repository.BaseRepository#countBySearchFilter(java.lang.String)
   */
  @Override
  public Long count() {
    Long count = 0L;
    String queryString = "select count(<%= repositoryClassPrefix %>.id) from <%= repositoryClass %> <%= repositoryClassPrefix %>";
    TypedQuery<Long> results = em.createQuery(queryString, Long.class);
    
    try {
      count = results.getSingleResult();
    } catch (NoResultException e) {}
    return count;
  }
<% } %>
}