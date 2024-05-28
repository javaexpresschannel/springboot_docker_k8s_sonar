package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.models.Category;
import com.javaexpress.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	// http://localhost:8050/api/category
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Category create( @RequestBody Category category) {
		return categoryService.createCategory(category);
	}
	
	@GetMapping
	public Iterable<Category> fetchAllCategories() {
		return categoryService.fetchCategories();
	}
	
	@GetMapping("/{id}")
	public Category fetchCategory(@PathVariable("id")Integer categoryId) {
		 return categoryService.fetchCategory(categoryId);
	}
	
	@PutMapping("/{id}")
	public void updateCategory(@PathVariable("id")Integer categoryId,
			@RequestBody Category category) {
		categoryService.updateCategory(categoryId,category);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable("id")Integer categoryId) {
		categoryService.deleteCategory(categoryId);
	}
	
	@GetMapping("/v1/{name}")
	public Category fetchCategoryByName(@PathVariable("name")String categoryName) {
		 return categoryService.fetchCategory(categoryName);
	}
	
	@GetMapping("/hello")
	public String getHello() {
		return "Hello Controller";
	}
}
