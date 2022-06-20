package com.vajro.shopify.product.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vajro.shopify.product.model.VariantKey;
import com.vajro.shopify.product.model.Variants;

@Repository
public interface VariantsRepository extends CrudRepository<Variants, VariantKey>{
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE VARIANTS SET INVENTORY_QUANTITY = ?3,PRICE=?4 WHERE ID = ?1 AND PRODUCT_ID = ?2 ",nativeQuery = true)
	public int updateQuantityAndPrice(String id,String productId,int inventoryQty,String price) throws Exception;

}
