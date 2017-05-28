package <%=packageName%>.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Version
  @Column(name = "version", nullable = false)
  private Long version = 0L;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_created", nullable = false, updatable = false)
  private Date dateCreated;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_updated", nullable = false)
  private Date lastUpdated;

  @Column(name = "created_by", updatable = false)
  private String createdBy;

  @Column(name = "updated_by")
  private String updatedBy;

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
   * Get the version
   *
   * @return Long
   */
  public Long getVersion() {
    return version;
  }

  /**
   * Set the version 
   *
   * @param Long version
   */
  public void setVersion(Long version) {
    this.version = version;
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

  @PrePersist
  protected void onCreate() {
    this.lastUpdated = this.dateCreated = new Date();
    this.updatedBy = this.createdBy = getCurrentUser();
  }

  @PreUpdate
  protected void onUpdate() {
    this.lastUpdated = new Date();
    this.updatedBy = getCurrentUser();
  }

  /**
   * Get the logged in user.
   * 
   * @return String
   */
  protected String getCurrentUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null || !auth.isAuthenticated())
      return null;
    return ((UserDetails) auth.getPrincipal()).getUsername();
  }

}
