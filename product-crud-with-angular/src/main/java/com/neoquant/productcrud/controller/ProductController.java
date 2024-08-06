package com.neoquant.productcrud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neoquant.productcrud.dto.APIResponse;
import com.neoquant.productcrud.dto.ProductRequest;
import com.neoquant.productcrud.exception.ProductNotFoundException;
import com.neoquant.productcrud.model.Product;
import com.neoquant.productcrud.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService service;
	
	@PostMapping(value = "/save")
		public Product addProduct(@RequestBody @Valid ProductRequest productRequest) {
		return service.saveProduct(productRequest);
	}
	
	@GetMapping
	public List<Product> getProducts(){
		return service.getProductList();
	}
	
	@GetMapping(value = "/getById/{id}")
	public Product getProductById(@PathVariable int id) throws ProductNotFoundException{
		return service.searchProductById(id);
	}
	
	@GetMapping(value = "/getByName/name")
	public List<Product> getProductByName(@RequestParam String name){
		return service.serachByName(name);
	}
	
	// Sorting Product Fields in Ascending Order
//	@GetMapping("/{field}")
//	public APIResponse<List<Product>> getProductsWithSort(@PathVariable String field){
//		List<Product> allProducts = service.findProductsWithSorting(field);
//		return new APIResponse<>(allProducts.size(), allProducts);
//	}
	
	// Sorting Product Fields in Descending Order by Name
	@GetMapping(value = "/{name}")
	public APIResponse<List<Product>> sortProductByName(@PathVariable String name){
		List<Product> descByName = service.findProductByDescSorting(name);
		return new APIResponse<>(descByName.size(), descByName);
	}
	
	//Product Page Pagination
	@GetMapping(value = "/{offset}/{pageSize}")
	public APIResponse<Page<Product>> getProductWithSort(@PathVariable int offset, @PathVariable int pageSize){
		Page<Product> pagination = service.findProductsWithPagination(offset, pageSize);
		return new APIResponse<>(pagination.getSize(), pagination);
	}

	
	@PutMapping(value = "/update/{id}")
	public Product updateProduct(@PathVariable int id ,@RequestBody ProductRequest productRequest) {
		return service.saveProduct(productRequest);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public Product deleteProduct(@PathVariable int id) throws ProductNotFoundException{
		return service.deleteProductById(id);
	}
}
