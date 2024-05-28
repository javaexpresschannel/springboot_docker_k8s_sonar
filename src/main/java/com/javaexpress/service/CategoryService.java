package com.javaexpress.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.exceptions.CategoryNotFoundException;
import com.javaexpress.models.Category;
import com.javaexpress.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Iterable<Category> fetchCategories() {
		return categoryRepository.findAll();
	}

	public Category fetchCategory(Integer categoryId) {
		Optional<Category> optional = categoryRepository.findById(categoryId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new CategoryNotFoundException("Category Not found in Database");
		}
	}

	public void deleteCategory(Integer categoryId) {
		var exisingRecord = fetchCategory(categoryId);
		categoryRepository.delete(exisingRecord);
	}

	public Category updateCategory(Integer categoryId, Category category) {
		var exisingRecord = fetchCategory(categoryId);
		exisingRecord.setName(category.getName());
		return categoryRepository.save(exisingRecord);
	}

	public Category fetchCategory(String categoryName) {
		return categoryRepository.findByName(categoryName);
	}
	
	
}
