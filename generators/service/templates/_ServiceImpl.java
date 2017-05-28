package <%=packageName%>.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional<% if(implementsBaseService == true) { %>
public class <%= serviceClass %>ServiceImpl implements <%= serviceClass %>Service {
<% } else { %>
public class <%= serviceClass %>ServiceImpl {
<% } %>
  private final Logger log = LoggerFactory.getLogger(<%= serviceClass %>Service.class);
<% if(implementsBaseService == true) { %>

  private <%= serviceClass %>Repository <%= serviceClass %>Repository <%= serviceClass %>Repository;

  @Autowired
  public <%= serviceClass %>ServiceImpl(<%= serviceClass %>Repository <%= serviceClass %>Repository) {
    this.<%= serviceClass %>Repository = <%= serviceClass %>Repository;
  }

  /* (non-Javadoc)
   * @see com.mattheeuws.myportal.service.BaseService#save(java.lang.Object)
   */
  @Override
  public <%= dtoClass %> save(<%= dtoClass %> dto) {
    <%= dtoClass %> retval = null;

    <%= entityClass %> result = <%= serviceClass %>Repository.save(
      mapTo(dto));

    if (result != null)
      retval = mapTo(result);

    return retval;
  }

  /* (non-Javadoc)
   * @see <%= packageName %>.service.BaseService#getById(java.lang.Long)
   */
  @Override
  public <%= dtoClass %> getById(Long id) {
    return mapTo(
      <%= serviceClass %>Repository.findById(id));
  }

  /* (non-Javadoc)
   * @see <%= packageName %>.service.BaseService#getAll()
   */
  @Override
  public List<<%= dtoClass %>> getAll() {
    return mapTo(
      <%= serviceClass %>Repository.getAll());
  }

  /* (non-Javadoc)
   * @see <%= packageName %>.service.BaseService#getAll(java.lang.Integer offset, java.lang.Integer max)
   */
  @Override
  public List<<%= dtoClass %>> getAll(Integer offset, Integer max) {
    return mapTo(
      <%= serviceClass %>Repository.findAll(offset, max));
  }

  /* (non-Javadoc)
   * @see <%= packageName %>.service.BaseService#count()
   */
  @Override
  public Long count() {
    return <%= serviceClass %>Repository.count();
  }

  /**
   * Map a Dto to an Entity.
   * 
   * @param <%= dtoClass %> dto
   * @return <%= entityClass %>
   */
  private <%= entityClass %> mapTo(<%= dtoClass %> dto) {
    <%= entityClass %> entity = <%= serviceClass %>Repository.findById(dto.getId());
    if (entity == null)
      entity = new <%= entityClass %>();

    // TODO: fillin the entity with data.
    return entity;
  }

  /**
   * Map an Entity to a Dto.
   *
   * @param <%= entityClass %> entity
   * @return <=% dtoClass %>
   */
  private <%= dtoClass %> mapTo(<%= entityClass %> entity) {
    <%= dtoClass %> dto = new <%= dtoClass %>();

    // TODO: fill in the dto with data.
    return dto;
  }

  /**
   * Map a list of Entities to a Dto list.
   * 
   * @param List<<%= entityClass %>> entities
   * @return List<<=% dtoClass %>>
   */
  private List<<=% dtoClass %>> mapTo(List<<%= entityClass %>> entities) {
    List<<=% dtoClass %>> retval = new ArrayList<<=% dtoClass %>>();
    if (entities == null || entities.isEmpty())
      return retval;
    for (<%= entityClass %> entity : entities) {
      retval.add(
        mapTo(entity));
    }
    return retval;
  }

<% } %>
}