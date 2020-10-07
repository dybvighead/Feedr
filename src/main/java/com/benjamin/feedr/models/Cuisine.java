package com.benjamin.feedr.models;

/**
 * @author benjamin
 * @version 1.3.0
 * @since 2020-10-06
 */
public class Cuisine {

	private String name;
	private String photo;

	Cuisine() {
	}

	/**
	 * Constructor to initialize the name and photo of the cuisine
	 * 
	 * @param name  : String
	 * @param photo : String
	 */
	Cuisine(String name, String photo) {
		this.name = name;
		this.photo = photo;
	}

	/**
	 * Returns the name of the cuisine
	 * 
	 * @return name : String
	 */
	public String getName() {
		return name;
	}

	/**
	 *  Returns the photo of the cuisine
	 * 
	 * @return photo : String
	 */
	public String getPhoto() {
		return photo;
	}

}
