package com.smartWorkers.gestionBudgets.services;

import com.smartWorkers.gestionBudgets.dao.CategoriesRepository;
import com.smartWorkers.gestionBudgets.entities.Categories;
import com.smartWorkers.gestionBudgets.entities.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoriesService {
    public List<Categories> getCategories();
    public Page<Categories> getCategoryInPages(int page, int size);
    public void deleteCategory(Long id);

    public Categories getCategoryById(Long categoryId);

    public void updateCategory(Categories category);
    public void addCategory(Categories category);
}
