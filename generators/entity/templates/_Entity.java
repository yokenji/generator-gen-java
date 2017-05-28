package <%=packageName%>.model;

import javax.persistence.Entity;
import javax.persistence.Table;#

@Entity
@Table(name = "<%= entityTableName %>")<% if (extendBaseEntity == true) { %>
public class <%= entityClass %><% if (extendBaseEntity == true) %> extends BaseEntity {
<% } else { %>
public class <%= entityClass %> implements Serializable {
<% } %>
  private static final long serialVersionUID = 1L;

} 