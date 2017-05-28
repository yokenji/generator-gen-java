package <%= packageName %>.repository;

import <%= packageName %>.model.<%= entityClass %>;
<% if (extendBaseRepository == true) { %>
public interface <%= repositoryClass %>Repository extends BaseRepository<<%= entityClass %>> {
<% } else { %>
public interface <%= repositoryClass %>Repository {
<% } %>
}
