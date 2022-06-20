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
@Table(name="images")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@IdClass(ImagesKey.class)
public class Images implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4749883702806566737L;

	@Id
	@JsonProperty("product_id")
	@Column(name = "product_id")
	private String productId;
	
	@Id
	@JsonProperty("id")
	@Column(name = "id")
    private String id;
	
	@JsonProperty("src")
	private String src;

}
