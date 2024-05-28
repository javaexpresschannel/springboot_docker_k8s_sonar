package com.javaexpress.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaexpress.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

	Category findByName(String name);
}
