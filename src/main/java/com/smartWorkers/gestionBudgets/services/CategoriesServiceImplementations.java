package com.smartWorkers.gestionBudgets.services;

import com.smartWorkers.gestionBudgets.dao.CategoriesRepository;
import com.smartWorkers.gestionBudgets.entities.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImplementations implements CategoriesService{
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Override
    public void addCategory(Categories category){
            categoriesRepository.save(category);
    }
    @Override
    public List<Categories> getCategories(){
        return categoriesRepository.findAll();
    }
    @Override
    public Page<Categories> getCategoryInPages(int page, int size){
        return categoriesRepository.findAll(PageRequest.of(page, size));
    }
    @Override
    public void deleteCategory(Long id){
        categoriesRepository.deleteById(id);
    }
    @Override
    public Categories getCategoryById(Long categoryId){
        return categoriesRepository.findById(categoryId).get();
    }
    @Override
    public void updateCategory(Categories category){
        categoriesRepository.save(category);
    }
	@Override
	public Long numberCategories() {
		return categoriesRepository.count();
	}
}
