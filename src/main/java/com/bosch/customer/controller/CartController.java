/**
 * 
 */
package com.bosch.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosch.customer.dto.Cart;
import com.bosch.customer.dto.CartDetails;
import com.bosch.customer.entity.Carts;
import com.bosch.customer.service.CartService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author BEN6KOR
 *
 */

@RestController
@RequestMapping("/cart")
@Api(tags = { "cart" })
public class CartController {
	private static final Logger LOG = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartService cartService;

	/**
	 * @param Cart
	 * @return
	 */
	@ApiOperation(value = "Use this API to get Cart Details ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/getCartInfo/{cartId}")
	public Carts getCartInfo(@PathVariable String cartId) {
		LOG.info("Get Cart details : {}", cartId);
		return cartService.getCartInfo(cartId);
	}

	@ApiOperation(value = "Use this API to get Cart Details ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/getCartInformation/{customerId}")
	public CartDetails getCartInformation(@PathVariable String customerId) {
		LOG.info("Get Cart details based on the customerId : {}", customerId);
		return cartService.getCartInformation(customerId);
	}

	/**
	 * @param productId
	 * @return
	 */
	@ApiOperation(value = "Use this API to delete cart details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@DeleteMapping(value = "/delete/{cartId}")
	public String deleteCart(@PathVariable String cartId) {
		LOG.info("Delete Cart  : {}", cartId);
		return cartService.deleteCart(cartId);
	}

	/**
	 * @param product
	 * @return
	 */
	@ApiOperation(value = "Use this API to post Cart Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String postCart(@RequestBody Cart cart) {
		LOG.info("Post Cart Id is : {}", cart.getProductId());
		return cartService.postCart(cart);
	}

	/**
	 * @param Cart
	 * @return
	 */
	@ApiOperation(value = "Use this API to get all Cart Details ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/getAllCartInfo")
	public List<Carts> getAllCartInfo() {
		LOG.info("Get all Cart details ");
		return cartService.getAllCartInfo();
	}

}
