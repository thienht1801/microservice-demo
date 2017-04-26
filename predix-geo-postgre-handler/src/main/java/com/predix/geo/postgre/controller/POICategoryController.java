package com.predix.geo.postgre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.predix.geo.postgre.dto.CategoryDTO;
import com.predix.geo.postgre.service.CategoryService;

@RestController
public class POICategoryController {
	
	@Autowired
	private CategoryService catService;

	@RequestMapping(value = "/poi_category", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> getCategoriesByName(@RequestParam("catName") String catName) {
		List<CategoryDTO> catogories = catService.getCategoryByName(catName);
		return new ResponseEntity<>(catogories, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/poi_all_category", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		List<CategoryDTO> catogories = catService.getCategories();
		return new ResponseEntity<>(catogories, HttpStatus.OK);
	}
}