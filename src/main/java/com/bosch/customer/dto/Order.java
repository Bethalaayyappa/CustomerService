/**
 * 
 */
package com.bosch.customer.dto;

/**
 * @author BEN6KOR
 *
 */
public class Order {

	private String orderNumber;
	private String customerId;
	private String cartId;
	private int price;
	private String status;

	/**
	 * 
	 */
	public Order() {
		super();
	}

	/**
	 * @param orderNumber
	 * @param customerId
	 * @param cartId
	 * @param price
	 * @param status
	 */
	public Order(String orderNumber, String customerId, String cartId, int price, String status) {
		super();
		this.orderNumber = orderNumber;
		this.customerId = customerId;
		this.cartId = cartId;
		this.price = price;
		this.status = status;
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
