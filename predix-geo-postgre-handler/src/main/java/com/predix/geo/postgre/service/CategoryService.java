package com.predix.geo.postgre.service;

import java.util.List;

import com.predix.geo.postgre.dto.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> getCategoryByName(String catName);
	
	List<CategoryDTO> getCategories();
}
