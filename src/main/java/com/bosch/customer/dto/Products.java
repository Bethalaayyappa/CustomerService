/**
 * 
 */
package com.bosch.customer.dto;

/**
 * @author BEN6KOR
 *
 */
public class Products {

	private String id;
	private String productName;
	private String onStock;
	private String category;
	private int price;

	/**
	 * 
	 */
	public Products() {
		super();
	}

	/**
	 * @param id
	 * @param productName
	 * @param onStock
	 * @param category
	 * @param price
	 */
	public Products(String id, String productName, String onStock, String category, int price) {
		super();
		this.id = id;
		this.productName = productName;
		this.onStock = onStock;
		this.category = category;
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
	 * @param onStock
	 *            the onStock to set
	 */
	public void setOnStock(String onStock) {
		this.onStock = onStock;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
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
	 * @return the onStock
	 */
	public String getOnStock() {
		return onStock;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
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
		return "Products [id=" + id + ", productName=" + productName + ", onStock=" + onStock + ", category=" + category
				+ ", price=" + price + "]";
	}

}
