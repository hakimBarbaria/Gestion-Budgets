package com.smartWorkers.gestionBudgets.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartWorkers.gestionBudgets.entities.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long>{

}
