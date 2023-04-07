package com.smartWorkers.gestionBudgets.services;

import com.smartWorkers.gestionBudgets.dao.CategoriesRepository;
import com.smartWorkers.gestionBudgets.entities.Categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImplem implements CategoryService {

    @Autowired
    private CategoriesRepository categoryRepository;

    @Override
    public Categories getCategorieById(Long category_id) {
    	return categoryRepository.findById(category_id).get();
    }

    @Override
    public Categories saveCategory(Categories category) {
        return categoryRepository.save(category);
    }

}
