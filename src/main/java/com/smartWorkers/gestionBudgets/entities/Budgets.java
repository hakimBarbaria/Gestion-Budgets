package com.smartWorkers.gestionBudgets.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Budgets {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long budget_id;
  private Double budgetLimit;
  private Date created_at;
  private Date updated_at;

  @ManyToOne
  private Categories categorie;

  public Long getBudget_id() {
    return budget_id;
  }

  public void setBudget_id(Long budget_id) {
    this.budget_id = budget_id;
  }

  public Double getbudgetLimit() {
    return budgetLimit;
  }

  public void setbudgetLimit(Double budgetLimit) {
    this.budgetLimit = budgetLimit;
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

  public Categories getCategorie() {
    return categorie;
  }

  public void setCategorie(Categories categorie) {
    this.categorie = categorie;
  }
}
