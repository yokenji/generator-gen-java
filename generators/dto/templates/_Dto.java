package <%= packageName %>.dto;
<% if (extendBaseDto == true) { %>
public class <%= dtoClass %>Dto extends BaseDto {
<% } else { %>
public class <%= dtoClass %>Dto {
<% } %>
  public <%= dtoClass %>Dto() {
    
  }

}