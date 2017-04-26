package com.predix.geo.postgre.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.predix.geo.postgre.dto.CategoryDTO;
import com.predix.geo.postgre.entity.Category;
import com.predix.geo.postgre.repo.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepo;
	
	@Override
	public List<CategoryDTO> getCategoryByName(String catName) {
		if(null != catName && !"".equals(catName)){
			return convertEntityToDTO(categoryRepo.getCategoriesByName(catName));
		}else{
			throw new IllegalArgumentException("Missing category name.");
		}
	}
	
	private List<CategoryDTO> convertEntityToDTO(List<Category> entities){
		List<CategoryDTO> dtoList = new ArrayList<CategoryDTO>();		
		if(null != entities && !entities.isEmpty()){
			for(Category category: entities){
				CategoryDTO dto = new CategoryDTO();
				dto.setCategoryId(category.getCatId());
				dto.setCatName(category.getCatName());
				dto.setCatClass(category.getCatClass());
				dto.setCatSubClass(category.getCatSubClass());
				dto.setCatCode(category.getCatCode());
				dto.setDescription(category.getDescription());
				dtoList.add(dto);
			}
		}		
		return dtoList;
	}

	@Override
	public List<CategoryDTO> getCategories() {
		return convertEntityToDTO((List<Category>) categoryRepo.findAll());
	}
}
