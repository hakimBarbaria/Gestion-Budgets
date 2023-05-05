package com.smartWorkers.gestionBudgets.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Categories {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long categorie_id;
  private String name;
  private String description;

  @OneToOne(mappedBy = "categorie")
  private Budgets budgets;

  @OneToMany(mappedBy = "categorie")
  private List<Transactions> transactions;

  public Categories() {
  }

  public Categories(String name, String description, Budgets budgets, List<Transactions> transactions,
      Users user) {
    super();
    this.name = name;
    this.description = description;
    this.budgets = budgets;
    this.transactions = transactions;
  }

  public List<Transactions> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transactions> transactions) {
    this.transactions = transactions;
  }

  public Long getCategorie_id() {
    return categorie_id;
  }

  public void setCategorie_id(Long categorie_id) {
    this.categorie_id = categorie_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Budgets getBudgets() {
    return budgets;
  }

  public void setBudgets(Budgets budgets) {
    this.budgets = budgets;
  }
}
