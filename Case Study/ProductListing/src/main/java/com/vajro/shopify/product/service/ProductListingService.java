package com.vajro.shopify.product.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProductListingService {

	String processRequest(String mode,List<String> prodIdList) throws JsonProcessingException,Exception;
}
