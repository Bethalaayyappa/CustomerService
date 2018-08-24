/**
 * 
 */
package com.bosch.customer.dto;

/**
 * @author BEN6KOR
 *
 */
public class CartDetails {

	private String id;
	private String productName;
	private String quantity;
	private String units;
	private int price;

	/**
	 * 
	 */
	public CartDetails() {
		super();
	}

	/**
	 * @param id
	 * @param productName
	 * @param quantity
	 * @param units
	 * @param price
	 */
	public CartDetails(String id, String productName, String quantity, String units, int price) {
		super();
		this.id = id;
		this.productName = productName;
		this.quantity = quantity;
		this.units = units;
		this.price = price;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param units
	 *            the units to set
	 */
	public void setUnits(String units) {
		this.units = units;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @return the units
	 */
	public String getUnits() {
		return units;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CartDetails [id=" + id + ", productName=" + productName + ", quantity=" + quantity + ", units=" + units
				+ ", price=" + price + "]";
	}

}
