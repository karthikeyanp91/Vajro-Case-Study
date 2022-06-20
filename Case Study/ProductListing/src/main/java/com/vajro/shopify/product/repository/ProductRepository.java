package com.vajro.shopify.product.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vajro.shopify.product.model.Products;

@Repository
public interface ProductRepository extends CrudRepository<Products, String>{

}
