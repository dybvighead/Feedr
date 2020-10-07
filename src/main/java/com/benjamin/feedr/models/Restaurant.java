package com.benjamin.feedr.models;

/**
 * @author benjamin
 * @version 1.3.0
 * @since 2020-10-06
 */
public class Restaurant {

	private String name;
	private String address;
	private String photo;
	private String priceLevel;
	private String rating;

	public Restaurant() {
	}

	/**
	 * Constructor to initialize restaurant
	 * 
	 * @param name       : String
	 * @param address    : String
	 * @param photo      : String
	 * @param priceLevel : String
	 * @param rating     : String
	 */
	public Restaurant(String name, String address, String photo, String priceLevel, String rating) {
		this.name = name;
		this.address = address;
		this.photo = photo;
		this.priceLevel = priceLevel;
		this.rating = rating;
	}

	/**
	 * Returns name of restaurant
	 * 
	 * @return name : String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns address of restaurant
	 * 
	 * @return address : String
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Returns photo of restaurant
	 * 
	 * @return photo : String
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * Returns price level of restaurant
	 * 
	 * @return price level : String
	 */
	public String getPriceLevel() {
		return priceLevel;
	}

	/**
	 * Returns rating of restaurant
	 * 
	 * @return rating : String
	 */
	public String getRating() {
		return rating;
	}

}
