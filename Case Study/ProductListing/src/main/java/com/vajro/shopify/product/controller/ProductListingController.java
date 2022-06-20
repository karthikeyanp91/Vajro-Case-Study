package com.vajro.shopify.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vajro.shopify.product.constants.ApplicationConstant;
import com.vajro.shopify.product.service.ProductListingService;

@RestController
@RequestMapping(path ="/vajroservice")
public class ProductListingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductListingController.class);
	
	@Autowired
	ProductListingService productService; 
	
	@GetMapping(path = "/ProductList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> fetchProductList(@RequestParam(name = "mode") String mode,
			@RequestParam(name = "prodIdList", required = false) List<String> prodIdList) throws JsonProcessingException,Exception {
		
		LOGGER.info("Inside fetchProductList mode :"+mode);
		String reponse = productService.processRequest(mode,prodIdList);
		if(!reponse.isEmpty()) {
			return new ResponseEntity<>(reponse, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(getResponseStr(ApplicationConstant.REST_INVALID_REQUEST), HttpStatus.BAD_REQUEST);
		}
	}
	
	public String getResponseStr(String resp) {
        return "{\"Webservice_Response\":\"" + resp + "\"}";
    }
}
