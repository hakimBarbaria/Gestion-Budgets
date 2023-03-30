package com.smartWorkers.gestionBudgets.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transactions {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transaction_id;
  private Double amount;
  private String type;
  private Date created_at;
  private Date updated_at;
  private String categorie;

  /*
   * @ManyToOne
   * 
   * @JoinColumn(name = "user_id", nullable = false)
   * private Users user;
   * 
   * @ManyToOne
   * 
   * @JoinColumn(name = "categorie_id", nullable = false)
   * private Categories categorie;
   */
  public Transactions() {
  }

  public Transactions(Double amount, String type, Date created_at, Date updated_at, String categorie) {
    this.categorie = categorie;
    this.amount = amount;
    this.type = type;
    this.created_at = created_at;
    this.updated_at = updated_at;
    /*
     * this.user = user;
     * this.categorie = categorie;
     */
  }

  public String getCategorie() {
    return categorie;
  }

  public void setCategorie(String categorie) {
    this.categorie = categorie;
  }

  public Long getTransaction_id() {
    return transaction_id;
  }

  public void setTransaction_id(Long transaction_id) {
    this.transaction_id = transaction_id;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public Date getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }
  /*
   * public Users getUser() {
   * return user;
   * }
   * public void setUser(Users user) {
   * this.user = user;
   * }
   * public Categories getCategorie() {
   * return categorie;
   * }
   * public void setCategorie(Categories categorie) {
   * this.categorie = categorie;
   * }
   */

}
