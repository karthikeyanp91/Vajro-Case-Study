package com.vajro.shopify.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="product_count")
@Data
public class ProductCount {
	
	@Id
	@Column(name = "count")
	private int count;

}
