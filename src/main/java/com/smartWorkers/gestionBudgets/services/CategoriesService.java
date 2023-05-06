package com.smartWorkers.gestionBudgets.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.smartWorkers.gestionBudgets.entities.Categories;

public interface CategoriesService {
  public List<Categories> getCategories();

  public Page<Categories> getCategoryInPages(int page, int size);

  public void deleteCategory(Long id);

  public Categories getCategoryById(Long categoryId);

  public void updateCategory(Categories category);

  public void addCategory(Categories category);
  
  public Long numberCategories();
}
