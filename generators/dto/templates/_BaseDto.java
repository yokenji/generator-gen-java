package <%=packageName%>.dto;

import java.util.Date;

public abstract class BaseDto {

  protected Long id;
  protected Date dateCreated;
  protected Date lastUpdated;
  protected String createdBy;
  protected String updatedBy;

  /**
   * Get the id
   *
   * @return Long
   */
  public Long getId() {
    return id;
  }

  /**
   * Set the id 
   *
   * @param Long id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Get the dateCreated
   *
   * @return Date
   */
  public Date getDateCreated() {
    return dateCreated;
  }

  /**
   * Set the dateCreated 
   *
   * @param Date dateCreated
   */
  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  /**
   * Get the lastUpdated
   *
   * @return Date
   */
  public Date getLastUpdated() {
    return lastUpdated;
  }

  /**
   * Set the lastUpdated 
   *
   * @param Date lastUpdated
   */
  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  /**
   * Get the createdBy
   *
   * @return String
   */
  public String getCreatedBy() {
    return createdBy;
  }

  /**
   * Set the createdBy 
   *
   * @param String createdBy
   */
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * Get the updatedBy
   *
   * @return String
   */
  public String getUpdatedBy() {
    return updatedBy;
  }

  /**
   * Set the updatedBy 
   *
   * @param String updatedBy
   */
  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

}