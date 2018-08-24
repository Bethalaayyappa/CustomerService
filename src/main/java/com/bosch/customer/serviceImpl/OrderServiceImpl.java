/**
 * 
 */
package com.bosch.customer.serviceImpl;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosch.customer.dao.OrderServiceRepository;
import com.bosch.customer.dto.Order;
import com.bosch.customer.entity.Orders;
import com.bosch.customer.service.OrderService;

/**
 * @author BEN6KOR
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderServiceRepository orderServiceRepository;

	@Override
	public String postOrder(Order order) {
		LOG.info("post order() .... : {} ", order.getCartId());
		String msg = null;
		Orders ordersTemp = null;
		if (order != null) {
			ordersTemp = setData(order);
		}
		boolean validations = validationOrder(ordersTemp);
		if (validations) {
			Orders orders = orderServiceRepository.save(ordersTemp);
			msg = orders != null ? "Order details Stored successfully " : "Order details not inserted";
		} else {
			msg = "Requested Parameters should not be null or empty or nospace";
		}

		return msg;
	}

	/**
	 * @param product
	 * @return
	 */
	private boolean validationOrder(Orders ordersTemp) {
		LOG.info("Validating product details : {}", ordersTemp);
		boolean validations = true;

		if (StringUtils.isBlank(ordersTemp.getId()) || StringUtils.isBlank(ordersTemp.getCartId())
				|| StringUtils.isBlank(ordersTemp.getCustomerId())
				|| StringUtils.isBlank(String.valueOf(ordersTemp.getPrice()))
				|| StringUtils.isBlank(ordersTemp.getOrderNumber()) || StringUtils.isBlank(ordersTemp.getStatus())) {
			validations = false;
		}
		return validations;
	}

	private Orders setData(Order order) {
		Orders prods = new Orders();
		prods.setId(UUID.randomUUID().toString());
		prods.setCartId(order.getCartId());
		prods.setCustomerId(order.getCustomerId());
		prods.setOrderNumber(order.getOrderNumber());
		prods.setPrice(order.getPrice());
		prods.setStatus(order.getStatus());

		return prods;
	}

	@Override
	public Orders getOrder(String orderId) {
		if (!StringUtils.isBlank(orderId)) {
			Orders orders = orderServiceRepository.findById(orderId);
			if (orders != null) {
				return orders;
			} else {
				LOG.info("Order Id is not found : {}", orderId);
			}
		} else {
			LOG.info("Order Id should not be null or Empty : {}", orderId);
		}
		return null;
	}

	@Override
	public String deleteOrder(String orderId) {
		if (!StringUtils.isBlank(orderId)) {
			Orders orders = orderServiceRepository.findById(orderId);
			if (orders != null) {
				orderServiceRepository.delete(orderId);
				return "Order details deleted successfully";
			} else {
				return "Order Id is not found";
			}

		}

		return "Requested Prameter should not be null or empty";
	}

}
