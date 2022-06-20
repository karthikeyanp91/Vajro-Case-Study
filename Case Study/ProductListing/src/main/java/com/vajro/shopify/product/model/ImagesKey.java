package com.vajro.shopify.product.model;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class ImagesKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1409201055864588761L;
	
	@Column(name = "product_id")
	private String productId;
	
	@Column(name = "id")
    private String id;

}
