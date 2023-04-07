package com.smartWorkers.gestionBudgets.services;

import com.smartWorkers.gestionBudgets.entities.Categories;
import com.smartWorkers.gestionBudgets.entities.Transactions;

public interface CategoryService {
    
    public Categories getCategorieById(Long category_id);
    
    public Categories saveCategory(Categories category);

}
