package com.vajro.shopify.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name="shop_info")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopInfo {

	@Id
	@Column(name = "name")
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("domain")
	@Column(name = "domain")
	private String domain;
}
