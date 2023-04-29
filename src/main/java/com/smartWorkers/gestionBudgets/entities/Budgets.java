package com.smartWorkers.gestionBudgets.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Budgets {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long budget_id;
  private Double budgetLimite;
  private Date created_at;
  private Date updated_at;
  /* Ajouter une clé étrangére "category_id" du la table categories */
  @ManyToOne
  private Categories categorie;

  public Budgets(Double budgetLimite, Date created_at, Date updated_at, Categories categorie) {
    super();
    this.budgetLimite = budgetLimite;
    this.created_at = created_at;
    this.updated_at = updated_at;
    this.categorie = categorie;
  }

  public Categories getCategorie() {
    return categorie;
  }

  public void setCategorie(Categories categorie) {
    this.categorie = categorie;
  }

  public Budgets() {
  }

  public Long getBudget_id() {
    return budget_id;
  }

  public void setBudget_id(Long budget_id) {
    this.budget_id = budget_id;
  }

  public Double getBudgetLimite() {
    return budgetLimite;
  }

  public void setBudgetLimite(Double budgetLimite) {
    this.budgetLimite = budgetLimite;
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
