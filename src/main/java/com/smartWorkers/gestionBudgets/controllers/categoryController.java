package com.smartWorkers.gestionBudgets.controllers;

import java.util.Arrays;
import java.util.List;

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
  public String editCategory(ModelMap modelMap) { /** , @RequestParam("idCategory") Long Category_id ***/
    Categories category = categoriesService.getCategoryById(1L);
    modelMap.addAttribute("category", category);
    modelMap.addAttribute("showIcons", this.showListIcons);
    if (this.showListIcons == false) {
      List<String> icons = Arrays.asList(
          "alarm", "archive", "arrow-down", "arrow-left", "arrow-right", "arrow-up", "at", "bag"

      );
      modelMap.addAttribute("icons", icons);
      this.showListIcons = true;
    }
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

    if (newCategory.getIcon() != null && !old_category.getIcon().equals(newCategory.getIcon())) {
      old_category.setIcon(newCategory.getIcon());
    }

    categoriesService.updateCategory(old_category);
    modelMap.addAttribute("message", "Category updated successfully !");
    // modelMap.addAttribute("categ", newCategory);
    return "editCategory";
  }

  @RequestMapping("/saveCategory")
  public String saveCategory(ModelMap modelMap,
      @RequestParam("name") String name,
      @RequestParam("icon") String icon) {
    Categories new_category = new Categories();
    new_category.setName(name);
    new_category.setIcon(icon);
    categoriesService.addCategory(new_category);
    return "redirect:/Dashboard";
  }
}
