package com.vajro.shopify.product.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name="products")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Products {

	@Id
	@JsonProperty("id")
	@Column(name = "id")
	private String id;
	
	@JsonProperty("title")
	@Column(name = "title")
    private String title;
	
	@JsonProperty("created_at")
	@Column(name = "created_at")
    private String createdAt;
	
	@JsonProperty("status")
	@Column(name = "status")
    private String status;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id")
	@JsonProperty("variants")
	private List<Variants> variantList;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id") 
	@JsonProperty("images")
	private List<Images> imageList;
	
	
}
