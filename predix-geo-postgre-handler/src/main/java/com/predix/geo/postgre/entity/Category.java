package com.predix.geo.postgre.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements java.io.Serializable {

	private static final long serialVersionUID = -2898068350077124133L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private Long catId;

	@Column(name = "category_name")
	private String catName;
	
	@Column(name = "category_class")
	private String catClass;

	@Column(name = "category_subclass")
	private String catSubClass;

	@Column(name = "category_code")
	private String catCode;

	@Column(name = "description")
	private String description;

	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
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
