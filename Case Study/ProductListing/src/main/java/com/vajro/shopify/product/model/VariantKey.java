package com.vajro.shopify.product.model;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class VariantKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2588150056637259775L;
	
	@Column(name = "product_id")
	private String productId;
	
	@Column(name = "id")
    private String id;

}
