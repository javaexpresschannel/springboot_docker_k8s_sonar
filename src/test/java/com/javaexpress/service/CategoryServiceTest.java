package com.javaexpress.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.javaexpress.exceptions.CategoryNotFoundException;
import com.javaexpress.models.Category;
import com.javaexpress.repository.CategoryRepository;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

	@Mock
	CategoryRepository categoryRepository;

	@InjectMocks
	CategoryService categoryService;

	// BDD Test
	// when
		// then
			// given

	@Test
	@DisplayName("Create Category Test Case")
	public void When_Create_Category_With_ValidInput_Then_Return_Success() {

		Category category = new Category();
		category.setName("Mobile");

		Category dbCategory = new Category();
		dbCategory.setId(123);
		dbCategory.setName("Mobile");
		

		// Mockito.when(categoryRepository.save(ArgumentMatchers.any(Category.class))
		// ).thenReturn(dbCategory);

		// we are mocking db call
		Mockito.when(categoryRepository.save(category)).thenReturn(dbCategory);

		var dbResult = categoryService.createCategory(category);
		assertEquals(dbResult.getName(), category.getName());
		assertNotNull(dbCategory.getId());
	}

	@Test
	@DisplayName("Create Category Test Case 1")
	public void When_Create_Category_With_ValidInput_Then_Return_InvalidData() {

		Category category = new Category();
		category.setName("Groceries");

		Category dbCategory = new Category();
		dbCategory.setId(125);
		dbCategory.setName("Mobile");

		Mockito.when(categoryRepository.save(ArgumentMatchers.any(Category.class))).thenReturn(dbCategory);

		var dbResult = categoryService.createCategory(category);
		assertNotEquals(dbResult.getName(), category.getName());
	}

	
	@Test
	@DisplayName("Create Category Test Case when Null Condition")
	public void When_Create_Category_With_Invalid_Name_Then_Throw_Exception() {

		Category category = new Category();
		category.setName(null);

		Category dbCategory = new Category();
		dbCategory.setId(125);
		dbCategory.setName(null);

		// ArgumentMatchers.anyString()
		// ArgumentMatchers.anyInt()
		Mockito.when(categoryRepository.save(ArgumentMatchers.any(Category.class))).thenReturn(dbCategory);

		var dbResult = categoryService.createCategory(category);
		// assertEquals(dbResult.getName(), category.getName());
	}

	@Test
	@DisplayName("Fetch Categoies Test Case")
	public void fetchCategoriesTest() {
		Mockito.when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
		var result = categoryService.fetchCategories();
		assertEquals(result,new ArrayList<>());
	}
	
	@Test
	public void fetchCategoryTest() {
		Category category = new Category();
		category.setId(111);
		category.setName("Mobile");
		Mockito.when(categoryRepository.findById(ArgumentMatchers.anyInt()))
			.thenReturn(Optional.of(category));
		Category dbCategory =categoryService.fetchCategory(111);
		assertEquals(dbCategory.getName(), category.getName());
		
	}
	
	@Test
	public void fetchCategoryTest1() {
		Category category = new Category();
		category.setId(111);
		category.setName("241vcvafafds");
		//assertThatExceptionOfType(CategoryNotFoundException.class)
		//	.isThrownBy(()->categoryService.fetchCategory(111));
		assertThrows(CategoryNotFoundException.class, 
				()->categoryService.fetchCategory(111));
	}
	
	@Test
	public void deleteCategoryTest() {
		Category category = new Category();
		category.setId(111);
		Mockito.when(categoryRepository.findById(ArgumentMatchers.anyInt()))
		.thenReturn(Optional.of(category));
		doNothing().when(categoryRepository)
			.delete(ArgumentMatchers.any(Category.class));
		categoryService.deleteCategory(1);
	}
	
}
