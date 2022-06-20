package com.vajro.shopify.product;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class ProductListingApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductListingApplication.class);
	
	@Value("${shopify.ws.contenttype}")
	private String wsContentType;
	@Value("${shopify.ws.accesstoken}")
	private String wsAccessToken;
	@Value("${shopify.ws.connecttimeout}")
	private int connectTimeout;
	@Value("${shopify.ws.readtimeout}")
	private int readTimeout;
	
	public static void main(String[] args) {
		LOGGER.info("# ProductListingApplication Started #");
		SpringApplication.run(ProductListingApplication.class, args);
	}
	
	@Bean
	public HttpEntity<String> httpEntity(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", wsContentType);
		headers.add("X-Shopify-Access-Token", wsAccessToken);
		return new HttpEntity<String>(headers);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
				.setConnectTimeout(Duration.ofMillis(connectTimeout))
				.setReadTimeout(Duration.ofMillis(readTimeout))
				.build();
	}

	@Bean
	public ObjectMapper getJacksonObjectMapper(){
		return new ObjectMapper();
	}
}
