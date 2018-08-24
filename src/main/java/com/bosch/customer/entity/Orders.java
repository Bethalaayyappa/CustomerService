/**
 * 
 */
package com.bosch.customer.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author BEN6KOR
 *
 */
@Document(collection = "orders")
public class Orders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String orderNumber;
	private String customerId;
	private String cartId;
	private int price;
	private String status;

	/**
	 * 
	 */
	public Orders() {
		super();
	}

	/**
	 * @param id
	 * @param orderNumber
	 * @param customerId
	 * @param cartId
	 * @param price
	 * @param status
	 */
	public Orders(String id, String orderNumber, String customerId, String cartId, int price, String status) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.customerId = customerId;
		this.cartId = cartId;
		this.price = price;
		this.status = status;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param orderNumber
	 *            the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @param cartId
	 *            the cartId to set
	 */
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @return the cartId
	 */
	public String getCartId() {
		return cartId;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

}
