package com.neoquant.productcrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.neoquant.productcrud.dto.ProductRequest;
import com.neoquant.productcrud.exception.ProductNotFoundException;
import com.neoquant.productcrud.model.Product;
import com.neoquant.productcrud.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repo;
	
	public Product saveProduct(ProductRequest productRequest) {
		Product product = null;
		if(productRequest.getId() == 0) {

			Product pro = new Product(productRequest.getId(), productRequest.getName(), productRequest.getQuantity(), 
					productRequest.getColor(), productRequest.getPrice(), productRequest.getWeight());
			System.out.println(pro);
			product= repo.save(pro);
		}else {
			Product existingProduct = repo.findProductById(productRequest.getId());
			Product pro = new Product(productRequest.getId(), productRequest.getName(), productRequest.getQuantity(), 
					productRequest.getColor(), productRequest.getPrice(), productRequest.getWeight());
			System.out.println(pro);
			product = repo.save(pro);
		}
		return product;
	}
	
	public List<Product> getProductList(){
		return repo.findAll();
	}
	
	public Product searchProductById(int id) throws ProductNotFoundException{
		Product product= repo.findById(id).get();
		if(product !=null) {
			return product;
		}else {
			throw new ProductNotFoundException("User not found with id :"+id);
		}
	}
	
	public List<Product> serachByName(String name){
		return repo.getProductByProductName(name);
	}
	
//	// Sorting Product Fields in Ascending Order
//	public List<Product> findProductsWithSorting(String field){
//		return repo.findAll(Sort.by(Sort.Direction.ASC,field));
//	}
	
	// Sorting Product Fields in Descending Order by Name
	public List<Product> findProductByDescSorting(String name){
		return repo.findAll(Sort.by(Sort.Direction.ASC, name));
	}
	
	// Product Pagination
	public Page<Product> findProductsWithPagination(int offset, int pageSize){
		Page<Product> pages= repo.findAll(PageRequest.of(offset, pageSize));
		return pages;
	}
	
	public Product deleteProductById(int id) throws ProductNotFoundException{
		Product product =searchProductById(id);
		if(product.getId() == id) {
			repo.deleteById(id);

			return product;
		}else {
			throw new ProductNotFoundException("User Not Found with id:"+id);
		}
		
	}
}
