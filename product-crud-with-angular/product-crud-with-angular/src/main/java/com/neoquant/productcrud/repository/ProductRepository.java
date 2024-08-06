package com.neoquant.productcrud.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neoquant.productcrud.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	public Product findProductById(int id);
	
	@Query("FROM Product p WHERE p.name LIKE %:name%")
	public List<Product> findByName(@Param("name") String name);
	
	
	// Create Query/ Custom Query
	@Query("select p from Product p where p.name=:name")
	public List<Product> getProductByProductName(@Param("name") String name);
}
