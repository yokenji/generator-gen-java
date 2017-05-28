package <%=packageName%>.service;

<%= extendsBaseService == true) { %>
public interface <%= serviceClass %>Service extends BaseService<<%= dtoClass %>> {
<% } else { %>
public interface <%= serviceClass %>Service {
<% } %>
}