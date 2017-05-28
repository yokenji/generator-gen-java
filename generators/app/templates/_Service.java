package <%=packageName%>.service;

<% if(extendBaseService == true) { %>
public interface <%= serviceClass %> extends BaseService<<%= dtoClass %>> {
<% } else { %>
public interface <%= serviceClass %> {
<% } %>
}