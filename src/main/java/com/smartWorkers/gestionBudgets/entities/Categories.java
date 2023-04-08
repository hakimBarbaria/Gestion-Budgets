package com.smartWorkers.gestionBudgets.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Categories {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long categorie_id;
	private String name;
	//Importer l'url du l'icon
	private String icon;
	/*@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
	@ManyToOne
    @JoinColumn(name = "budget_id", nullable = false)
    private Budgets budget;*/
	
	public Categories() {
	}

	public Categories(String name, String icon, Users user, Budgets budget) {
		this.name = name;
		this.icon = icon;
		/*this.user = user;
		this.budget = budget;*/
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	/*public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Budgets getBudget() {
		return budget;
	}

	public void setBudget(Budgets budget) {
		this.budget = budget;
	}*/
	
	
	
}
