package com.benjamin.feedr.models;

import java.util.ArrayList;
import java.util.List;

import com.benjamin.feedr.controllers.FeedrController;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author benjamin
 * @version 1.3.0
 * @since 2020-10-06
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Candidates {

	/**
	 * Replace "candidates" with "results" if finding multiple restaurants
	 * {@Link FeedrController}
	 */
	@JsonProperty("candidates")
	public List candidateJsonList;
	public static List<Restaurant> candidateList = new ArrayList<>();

	/**
	 * Returns a list of restaurants from Google Places from Json format
	 * 
	 * @return candidateJsonList : List
	 */
	public List getCandidateJsonList() {
		return candidateJsonList;
	}

	/**
	 * Returns list of restaurant objects
	 * 
	 * @return candidateList : List<Restaurant>
	 */
	public static List<Restaurant> getCandidates() {
		return candidateList;
	}

	/**
	 * Returns list of resturant names
	 * 
	 * @return candidateNames : List<String>
	 */
	public static List<String> getAllCandidateNames() {
		List<String> candidateNames = new ArrayList<>();
		for (Restaurant restaurant : candidateList) {
			candidateNames.add(restaurant.getName());
		}
		return candidateNames;
	}
}
