package com.vajro.shopify.product.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name="variants")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@IdClass(VariantKey.class)
public class Variants implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = -1366191201117769084L;

	@Id
	@JsonProperty("product_id")
	@Column(name = "product_id")
	private String productId;
	
	@Id
	@JsonProperty("id")
	@Column(name = "id")
    private String id;
	
	@JsonProperty("title")
	@Column(name = "title")
	private String title;
	
	@JsonProperty("price")
	@Column(name = "price")
	private String price;
	
	@JsonProperty("compare_at_price")
	@Column(name = "compare_at_price")
	private String compareAtPrice;
	
	@JsonProperty("inventory_item_id")
	@Column(name = "inventory_item_id")
	private String inventoryItemId;
	
	@JsonProperty("inventory_quantity")
	@Column(name = "inventory_quantity")
	private int inventoryQuantity;
	
	@JsonProperty("requires_shipping")
	@Column(name = "requires_shipping")
	private boolean requiresShipping;

}
