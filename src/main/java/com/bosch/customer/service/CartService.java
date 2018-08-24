/**
 * 
 */
package com.bosch.customer.service;

import java.util.List;

import com.bosch.customer.dto.Cart;
import com.bosch.customer.dto.CartDetails;
import com.bosch.customer.entity.Carts;

/**
 * @author BEN6KOR
 *
 */
public interface CartService {

	String postCart(Cart cart);

	Carts getCartInfo(String cartId);

	String deleteCart(String cartId);

	CartDetails getCartInformation(String customerId);

	List<Carts> getAllCartInfo();

}
