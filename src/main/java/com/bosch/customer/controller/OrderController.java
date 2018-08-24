/**
 * 
 */
package com.bosch.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosch.customer.dto.Order;
import com.bosch.customer.entity.Orders;
import com.bosch.customer.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author BEN6KOR
 *
 */

@RestController
@RequestMapping("/orders")
@Api(tags = { "Orders" })
public class OrderController {

	private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	/**
	 * @param productName
	 * @return
	 */
	@ApiOperation(value = "Use this API to get Order Details ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/getOrderInfo/{orderId}")
	public Orders getOrderInfo(@PathVariable String orderId) {
		LOG.info("Get Order details : {}", orderId);
		return orderService.getOrder(orderId);
	}

	/**
	 * @param productId
	 * @return
	 */
	@ApiOperation(value = "Use this API to delete order details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/delete/{orderId}")
	public String deleteOrder(@PathVariable String orderId) {
		LOG.info("Delete Order  : {}", orderId);
		return orderService.deleteOrder(orderId);
	}

	/**
	 * @param product
	 * @return
	 */
	@ApiOperation(value = "Use this API to post Order Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String postOrder(@RequestBody Order order) {
		LOG.info("Post Order Cart Id is : {}", order.getCartId());
		return orderService.postOrder(order);
	}

}
