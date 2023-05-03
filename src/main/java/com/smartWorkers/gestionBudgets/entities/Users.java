package com.smartWorkers.gestionBudgets.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Users {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String email;
    private String password;
    private Double balance;
    private Double remember_token;
    private Date created_at;
    private Date updated_at;
    
    @OneToMany(mappedBy = "user")
    private List<Transactions> transactions;
    
    @OneToMany(mappedBy = "user")
    private List<Categories> categories;
    
    @OneToMany(mappedBy = "user")
    private List<Budgets> budgets;
    
    public Users() {
    }
    
   
    public List<Transactions> getTransactions() {
		return transactions;
	}


	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}


	public List<Categories> getCategories() {
		return categories;
	}


	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}


	public List<Budgets> getBudgets() {
		return budgets;
	}


	public void setBudgets(List<Budgets> budgets) {
		this.budgets = budgets;
	}


	public Users(String email, String password, Double balance, Double remember_token, Date created_at, Date updated_at,
			List<Transactions> transactions, List<Categories> categories, List<Budgets> budgets) {
		super();
		this.email = email;
		this.password = password;
		this.balance = balance;
		this.remember_token = remember_token;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.transactions = transactions;
		this.categories = categories;
		this.budgets = budgets;
	}


	public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(Double remember_token) {
        this.remember_token = remember_token;
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
