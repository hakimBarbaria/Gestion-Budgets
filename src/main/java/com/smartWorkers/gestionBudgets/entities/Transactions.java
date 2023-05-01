package com.smartWorkers.gestionBudgets.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Transactions {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transaction_id;
  private Double amount;
  private String type;
  private Date created_at;
  private Date updated_at;
  private String description;

  @ManyToOne
  private Categories categorie;
  
  public Categories getCategorie() {
	return categorie;
}



public Transactions() {
  }

  

  public void setCategorie(Categories categorie) {
	this.categorie = categorie;
}



public Transactions(Double amount, String type, Date created_at, Date updated_at, String description,
		Categories categorie) {
	super();
	this.amount = amount;
	this.type = type;
	this.created_at = created_at;
	this.updated_at = updated_at;
	this.description = description;
	this.categorie = categorie;
}



public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
}
