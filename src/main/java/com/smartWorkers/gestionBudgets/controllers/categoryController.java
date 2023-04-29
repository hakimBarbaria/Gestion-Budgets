package com.smartWorkers.gestionBudgets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
  boolean ChangeViewList = true;

  @Autowired
  CategoriesService categoriesService;

  @RequestMapping("/Categories")
  public String RedirectToCategories(ModelMap modelMap,
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "3") int size) {
    /* declaration des page des categories */
    Page<Categories> categories = categoriesService.getCategoryInPages(page, size);
    modelMap.addAttribute("categories", categories);
    modelMap.addAttribute("pages", new int[categories.getTotalPages()]);
    modelMap.addAttribute("currentPage", page);
    if (this.ChangeViewList == false) {
      return "categories";
    } else {
      return "CategoriesInTables";
    }
  }

  @RequestMapping("changingTypeView")
  public String changingTypeView() {
    if (this.ChangeViewList == false) {
      this.ChangeViewList = true;
    } else {
      this.ChangeViewList = false;
    }
    return "redirect:/Categories";
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
  public String editCategory(ModelMap modelMap, @RequestParam("idCategory") Long Category_id) {
    Categories category = categoriesService.getCategoryById(Category_id);
    modelMap.addAttribute("category", category);
    return "editCategory";
  }

  @RequestMapping("ListIcons")
  public String ListIcons(ModelMap modelMap) {
    return "redirect:/editCategory";
  }

  @RequestMapping("/updateCategory")
  public String updateCategory(ModelMap modelMap, @ModelAttribute("category") Categories newCategory) {

    Long category_id = newCategory.getCategorie_id();
    Categories old_category = categoriesService.getCategoryById(category_id);

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
    if(name.length()>=5&&description.length()>=10)
    {
      new_category.setName(name);
      new_category.setDescription(description);
      categoriesService.addCategory(new_category);
      return "redirect:/Dashboard";
    }
    else if(name.length()<5&&description.length()<10){
      modelMap.addAttribute("messageBoth", "verify your input !");
      return "AddCategories";
    }
    else if(name.length()<5){
      modelMap.addAttribute("messageName", "the name must be more than 5 chars long");
      return "AddCategories";
    }
    else if(description.length()<10){
      modelMap.addAttribute("messageDesc", "the description must be more than 10 chars long");
      return "AddCategories";
    }
    return "";
  }
}
