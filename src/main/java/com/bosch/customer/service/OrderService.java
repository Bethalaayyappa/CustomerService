/**
 * 
 */
package com.bosch.customer.service;

import com.bosch.customer.dto.Order;
import com.bosch.customer.entity.Orders;

/**
 * @author BEN6KOR
 *
 */
public interface OrderService {

	String postOrder(Order order);

	Orders getOrder(String orderId);

	String deleteOrder(String orderId);

}
