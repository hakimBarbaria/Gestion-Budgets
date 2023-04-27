package com.smartWorkers.gestionBudgets.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartWorkers.gestionBudgets.entities.Categories;
import com.smartWorkers.gestionBudgets.services.CategoriesService;

@Controller
public class categoryController {
  boolean showListIcons = true;

  @Autowired
  CategoriesService categoriesService;

  @RequestMapping("/Categories")
  public String RedirectToCategories() {
    return "categories";
  }

  @RequestMapping("/AddCategory")
  public String RedirectToAddCategory() {
    return "AddCategories";
  }

  @RequestMapping(path = "/delete_category/{id}")
  public String deleteCategory(ModelMap modelMap, @PathVariable Long category_id) {
    categoriesService.deleteCategory(category_id);
    modelMap.addAttribute("message", "Category deleted successfully !");
    return "redirect:/categories";
  }

  @RequestMapping("/editCategory")
  public String editCategory(ModelMap modelMap) { /** , @RequestParam("idCategory") Long Category_id**/
    Categories category = categoriesService.getCategoryById(1L);
    modelMap.addAttribute("category", category);
    return "editCategory";
  }
 

  
  
  @RequestMapping("ListIcons")
  public String ListIcons(ModelMap modelMap) {
    this.showListIcons = false;
    return "redirect:/editCategory";
  }

  @RequestMapping("/updateCategory")
  public String updateCategory(ModelMap modelMap, @ModelAttribute("category") Categories newCategory) {

    // Long category_id = newCategory.getCategorie_id();
    Categories old_category = categoriesService.getCategoryById(1L);

    if (newCategory.getName() != null && !old_category.getName().equals(newCategory.getName())) {
      old_category.setName(newCategory.getName());
    }

    if (newCategory.getDescription() != null && !old_category.getDescription().equals(newCategory.getDescription())) {
      old_category.setDescription(newCategory.getDescription());
    }

    categoriesService.updateCategory(old_category);
    modelMap.addAttribute("message", "Category updated successfully !");
    // modelMap.addAttribute("categ", newCategory);
    return "editCategory";
  }

  @RequestMapping("/saveCategory")
  public String saveCategory(ModelMap modelMap,
      @RequestParam("name") String name,
      @RequestParam("description") String description) {
    Categories new_category = new Categories();
    new_category.setName(name);
    new_category.setDescription(description);
    categoriesService.addCategory(new_category);
    return "redirect:/Dashboard";
  }
}
