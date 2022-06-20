package com.vajro.shopify.product.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@Data
public class ProductListingResponse {
	
	@JsonProperty("shop_info")
	private ShopInfo shopInfo;
	
	@JsonProperty("product_count")
	private int productCount;
	
	@JsonProperty("products")
	private List<Products> productList;

}
