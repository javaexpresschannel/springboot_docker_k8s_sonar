package com.javaexpress.controller;

import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaexpress.models.Category;
import com.javaexpress.service.CategoryService;

@ExtendWith(SpringExtension.class)
public class CategoryControllerTest {

	private static MockMvc mockmvc;
	
	@Mock
	private CategoryService categoryService;
	
	@InjectMocks
	private CategoryController categoryController;
	
	@BeforeEach
	public void before() {
		mockmvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}
	
	@Test
	public void When_Create_PostRequest_With_ValidInput_Then_Return_Success() throws Exception {
		Category category = new Category();
		category.setName("Mobile");
		
		ObjectMapper obj = new ObjectMapper();
		Mockito.when(categoryService.createCategory(ArgumentMatchers.any(Category.class)))
			.thenReturn(category);
		
		//categoryController.create(category);
		//http://localhost:8080/api/category
		
		mockmvc.perform(MockMvcRequestBuilders.post("/api/category")
						.content(obj.writeValueAsString(category))
						.contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(MockMvcResultMatchers.status().isCreated());

	}
	
	@Test
	public void createCategory1() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		mockmvc.perform(MockMvcRequestBuilders.post("/api1/category1")
						.content(obj.writeValueAsString(null))
						.contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(MockMvcResultMatchers.status().isNotFound());

	}
	
	@Test
	public void fetchAllCategories() throws Exception {
		Category category = new Category();
		category.setName("Mobile");
		List<Category> list = new ArrayList<>();
		list.add(category);
		Mockito.when(categoryService.fetchCategories()).thenReturn(list);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/category"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void fetchAllCategories1() throws Exception {
		mockmvc.perform(MockMvcRequestBuilders.get("/api/category1"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void fetchCategoryTest() throws Exception {
		Category category = new Category();
		category.setName("Mobile");
		Mockito.when(categoryService.fetchCategory(ArgumentMatchers.anyInt()))
					.thenReturn(category);
		
		mockmvc.perform(MockMvcRequestBuilders.get("/api/category/{id}",23))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void deleteCategoryTest() throws Exception {
		doNothing().when(categoryService).deleteCategory(ArgumentMatchers.anyInt());
		mockmvc.perform(MockMvcRequestBuilders.delete("/api/category/{id}",1))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	
	
	
}
