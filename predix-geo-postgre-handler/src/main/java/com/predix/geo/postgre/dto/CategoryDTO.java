package com.predix.geo.postgre.dto;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = -3265382478625696537L;

	private Long categoryId;
	private String catName;
	private String catClass;
	private String catSubClass;
	private String catCode;
	private String description;
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatClass() {
		return catClass;
	}
	public void setCatClass(String catClass) {
		this.catClass = catClass;
	}
	public String getCatSubClass() {
		return catSubClass;
	}
	public void setCatSubClass(String catSubClass) {
		this.catSubClass = catSubClass;
	}
	public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
