/**
 * 
 */
package com.bosch.customer.serviceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bosch.customer.dao.CartServiceRepository;
import com.bosch.customer.dto.Cart;
import com.bosch.customer.dto.CartDetails;
import com.bosch.customer.dto.Products;
import com.bosch.customer.entity.Carts;
import com.bosch.customer.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author BEN6KOR
 *
 */
@Service
public class CartServiceImpl implements CartService {

	private static final Logger LOG = LoggerFactory.getLogger(CartServiceImpl.class);

	@Autowired
	private CartServiceRepository cartServiceRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public String postCart(Cart cart) {
		LOG.info("post Cart() .... : {} ", cart.getProductId());
		String msg = null;
		Carts cartsTemp = null;
		if (cart != null) {
			cartsTemp = setData(cart);
		}
		boolean validations = validationCart(cartsTemp);
		if (validations) {
			Carts carts = cartServiceRepository.save(cartsTemp);
			msg = carts != null ? "Cart details Stored successfully " : "Cart details not inserted";
		} else {
			msg = "Requested Parameters should not be null or empty or no space";
		}

		return msg;
	}

	/**
	 * @param product
	 * @return
	 */
	private boolean validationCart(Carts cartTemp) {
		LOG.info("Validating cart details : {}", cartTemp);
		boolean validations = true;

		if (StringUtils.isBlank(cartTemp.getId()) || StringUtils.isBlank(cartTemp.getProductId())
				|| StringUtils.isBlank(cartTemp.getQuantity()) || StringUtils.isBlank(cartTemp.getCustomerId())) {
			validations = false;
		}
		return validations;
	}

	private Carts setData(Cart cart) {
		Carts prods = new Carts();
		prods.setId(UUID.randomUUID().toString());
		prods.setCustomerId(cart.getCustomerId());
		prods.setProductId(cart.getProductId());
		prods.setQuantity(cart.getQuantity());

		return prods;
	}

	@Override
	public Carts getCartInfo(String cartId) {
		if (!StringUtils.isBlank(cartId)) {
			Carts carts = cartServiceRepository.findById(cartId);
			if (carts != null) {
				return carts;
			} else {
				LOG.info("Cart Id is not found : {}", cartId);
			}
		} else {
			LOG.info("Cart Id should not be null or empty : {}", cartId);
		}
		return null;
	}

	@Override
	public String deleteCart(String cartId) {
		if (!StringUtils.isBlank(cartId)) {
			Carts carts = cartServiceRepository.findById(cartId);
			if (carts != null) {
				cartServiceRepository.delete(cartId);
				return "Cart details deleted successfully";
			} else {
				return "Cart Id is not found";
			}

		}

		return "Requested Prameter should not be null or empty";
	}

	@Override
	@HystrixCommand(fallbackMethod = "callProductServiceFallBack")
	public CartDetails getCartInformation(String customerId) {

		ObjectMapper mapper = new ObjectMapper();
		Products products;
		CartDetails cartDetails = new CartDetails();
		if (!StringUtils.isBlank(customerId)) {
			Carts carts = cartServiceRepository.findByCustomerId(customerId);
			if (carts != null) {

				String response = restTemplate.exchange("http://localhost:8073/products/getProduct/{productId}",
						HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
						}, carts.getProductId()).getBody();
				try {
					products = mapper.readValue(response, Products.class);
					cartDetails.setId(carts.getId());
					cartDetails.setPrice(products.getPrice());
					cartDetails.setProductName(products.getProductName());
					cartDetails.setQuantity(carts.getQuantity());
					cartDetails.setUnits(carts.getQuantity());
					LOG.info("Products is : {}", products.getProductName());
				} catch (IOException e) {
					LOG.info("Exception occured  : {}", e.getMessage());
				}

			} else {
				LOG.info("customerId is not found : {}", customerId);
			}
		} else {
			LOG.info("Requested parameter should not be null or empty ");
		}
		return cartDetails;
	}

	@SuppressWarnings("unused")
	private CartDetails callProductServiceFallBack(String customerId) {
		CartDetails cartDetails = new CartDetails();
		System.out.println("Product Service is down!!! fallback route enabled...");

		LOG.info("CIRCUIT BREAKER ENABLED!!! No Response From Product Service at this moment. "
				+ " Service will be back shortly -{} ", new Date());
		return cartDetails;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bosch.customer.service.CartService#getAllCartInfo()
	 */
	@Override
	public List<Carts> getAllCartInfo() {
		List<Carts> cartList = cartServiceRepository.findAll();
		if (cartList.size() != 0) {
			return cartList;
		} else {
			LOG.info("Cart list is empty");
		}
		return null;

	}
}
