package com.vajro.shopify.product.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vajro.shopify.product.constants.ApplicationConstant;
import com.vajro.shopify.product.model.ProductCount;
import com.vajro.shopify.product.model.ProductListingResponse;
import com.vajro.shopify.product.model.Products;
import com.vajro.shopify.product.model.ShopInfo;
import com.vajro.shopify.product.model.Variants;
import com.vajro.shopify.product.repository.ProductCountRepository;
import com.vajro.shopify.product.repository.ProductRepository;
import com.vajro.shopify.product.repository.ShopInfoRepository;
import com.vajro.shopify.product.repository.VariantsRepository;
import com.vajro.shopify.product.service.ProductListingService;
import com.vajro.shopify.product.service.helper.ProductListingServiceHelper;

@Service
public class ProductListingServiceImpl implements ProductListingService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductListingServiceImpl.class);
	
	@Autowired
	private ProductListingServiceHelper productServiceHelper;
	@Autowired
	private ObjectMapper objMapper;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private ProductCountRepository prodCountRepo;
	@Autowired
	private ShopInfoRepository shopInfoRepo;
	@Autowired
	private VariantsRepository variantRepo;

	@Override
	public String processRequest(String mode,List<String> prodIdList) throws JsonProcessingException,Exception {
		
		ObjectWriter ow = objMapper.writer().withDefaultPrettyPrinter();
		String prodListRespStr = "";
		ProductListingResponse prodListResp = null;
		
		if(mode.equals(ApplicationConstant.FETCH_NEW_MODE)) {
			prodListResp = getServiceData();
		}
		else if(mode.equals(ApplicationConstant.FETCH_QUICK_MODE)) {
			prodListResp = getDatabaseData();
		}
		else if(mode.equals(ApplicationConstant.REFRESH_MODE) && prodIdList != null && prodIdList.size() > 0){

			String id ="";
			String productId ="";
			int invQty =0;
			String price ="";
			for(String prodId : prodIdList) {
				List<Variants> variantList = productServiceHelper.getProductVariant(prodId);
				for(Variants variant : variantList) {
					id = variant.getId();
					productId =variant.getProductId();
					invQty =variant.getInventoryQuantity();
					price =variant.getPrice();
					
					variantRepo.updateQuantityAndPrice(id,productId,invQty,price);
					LOGGER.info("Updated id:"+id+": productId :"+productId+": invQty :"+invQty+": price :"+price);
				}
			}
			
			prodListResp = getDatabaseData();
		}
		if(prodListResp != null) {
			prodListRespStr = ow.writeValueAsString(prodListResp);
		}
		
		LOGGER.info("ProductListingResponse :"+prodListRespStr);
		return prodListRespStr;
	}
	
	private ProductListingResponse getServiceData() throws JsonProcessingException {
		
		List<Products> productList = productServiceHelper.getAllProducts();
		ShopInfo shopObj = productServiceHelper.getShopInfo();
		int productCount = productServiceHelper.getProductCount();
		ProductCount prodCnt = new ProductCount();
		prodCnt.setCount(productCount);
		
		ProductListingResponse prodListResp = new ProductListingResponse();
		prodListResp.setProductList(productList); 
		prodListResp.setShopInfo(shopObj);
		prodListResp.setProductCount(productCount);
		
		productRepo.saveAll(productList);
		shopInfoRepo.save(shopObj);
		prodCountRepo.save(prodCnt);
		
		return prodListResp;
	}
	
	private ProductListingResponse getDatabaseData() throws JsonProcessingException {

		List<Products> productsList = (List<Products>)productRepo.findAll();
		List<ShopInfo> shopList = (List<ShopInfo>)shopInfoRepo.findAll();
		prodCountRepo.findAll();
		List<ProductCount> prodCntList = (List<ProductCount>)prodCountRepo.findAll();

		ProductListingResponse prodListResp = new ProductListingResponse();
		prodListResp.setProductList(productsList); 
		prodListResp.setShopInfo(shopList.get(0));
		prodListResp.setProductCount(prodCntList.get(0).getCount());

		return prodListResp;
	}
}
