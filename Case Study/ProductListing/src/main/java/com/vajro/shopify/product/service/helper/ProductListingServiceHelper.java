package com.vajro.shopify.product.service.helper;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vajro.shopify.product.model.Products;
import com.vajro.shopify.product.model.ShopInfo;
import com.vajro.shopify.product.model.Variants;

@Service
public class ProductListingServiceHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductListingServiceHelper.class);
	
	@Value("${shopify.allproducts.wsurl}")
	private String wsAllProductsURL;
	@Value("${shopify.shopinfo.wsurl}")
	private String wsShopInfoURL;
	@Value("${shopify.productcount.wsurl}")
	private String wsProductCount;
	@Value("${shopify.productvariant.wsurl}")
	private String wsProductVariant;
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HttpEntity<String> httpEntity;
	@Autowired
	private ObjectMapper objMapper;
	
	public List<Products> getAllProducts() throws JsonProcessingException {
		LOGGER.info("getAllProducts API URL :"+wsAllProductsURL);
		ResponseEntity<String> serviceResult = restTemplate.exchange(wsAllProductsURL, HttpMethod.GET, httpEntity, String.class);
		String respBody = serviceResult.getBody();
		LOGGER.info("getAllProducts Status :"+serviceResult.getStatusCode());
		
		String respStr = new JSONObject(respBody).getJSONArray("products").toString();
		List<Products> productsList = objMapper.readValue(respStr,  new TypeReference<List<Products>>(){});
		return productsList;
	}
	
	public ShopInfo getShopInfo() throws JsonProcessingException {
		LOGGER.info("getShopInfo API URL :"+wsShopInfoURL);
		ResponseEntity<String> serviceResult = restTemplate.exchange(wsShopInfoURL, HttpMethod.GET, httpEntity, String.class);
		String respBody = serviceResult.getBody();
		LOGGER.info("getShopInfo Status :"+serviceResult.getStatusCode());
		
		String respStr = new JSONObject(respBody).getJSONObject("shop").toString();
		ShopInfo shop = objMapper.readValue(respStr, ShopInfo.class);
		return shop;
	}
	
	public int getProductCount() {
		LOGGER.info("getProductCount API URL :"+wsProductCount);
		ResponseEntity<String> serviceResult = restTemplate.exchange(wsProductCount, HttpMethod.GET, httpEntity, String.class);
		String respBody = serviceResult.getBody();
		LOGGER.info("getProductCount Status :"+serviceResult.getStatusCode());
		
		int count = new JSONObject(respBody).getInt("count");
		LOGGER.info("getProductCount count :"+count);
		return count;
	}
	
	public List<Variants> getProductVariant(String productId) throws JsonProcessingException {
		String wsVariantURL = wsProductVariant.replace("#", productId);
		LOGGER.info("getProductVariant API URL :"+wsVariantURL);
		ResponseEntity<String> serviceResult = restTemplate.exchange(wsVariantURL, HttpMethod.GET, httpEntity, String.class);
		String respBody = serviceResult.getBody();
		LOGGER.info("getProductVariant Status :"+serviceResult.getStatusCode()); 
		
		String respStr = new JSONObject(respBody).getJSONArray("variants").toString();
		List<Variants> variantsList = objMapper.readValue(respStr,  new TypeReference<List<Variants>>(){});
		return variantsList;
	}
}
