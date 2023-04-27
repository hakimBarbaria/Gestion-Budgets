package com.smartWorkers.gestionBudgets.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categories {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long categorie_id;
  private String name;
 
  private String description;
  
  @OneToMany(mappedBy = "categorie")
  private List<Budgets> budgets;
  
  public Categories() {
  }


  public List<Budgets> getBudgets() {
	return budgets;
}


public void setBudgets(List<Budgets> budgets) {
	this.budgets = budgets;
}


public void setCategorie_id(Long categorie_id) {
	this.categorie_id = categorie_id;
}


public Categories(String name, String description, List<Budgets> budgets) {
	super();
	this.name = name;
	this.description = description;
	this.budgets = budgets;
}

public Long getCategorie_id() {
    return categorie_id;
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

  

  /*
   * public Users getUser() {
   * return user;
   * }
   * 
   * public void setUser(Users user) {
   * this.user = user;
   * }
   * 
   * public Budgets getBudget() {
   * return budget;
   * }
   * 
   * public void setBudget(Budgets budget) {
   * this.budget = budget;
   * }
   */

}
