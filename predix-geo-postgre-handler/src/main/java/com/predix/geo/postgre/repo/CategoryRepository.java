package com.predix.geo.postgre.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.predix.geo.postgre.entity.Category;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>, JpaSpecificationExecutor<Category> {

	@Query("SELECT c FROM Category c WHERE lower(c.catName) = lower(:catName)")
	public List<Category> getCategoriesByName(@Param("catName") String catName);

}