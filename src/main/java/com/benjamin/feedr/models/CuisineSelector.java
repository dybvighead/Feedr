package com.benjamin.feedr.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author benjamin
 * @version 1.3.0
 * @since 2020-10-06
 */
public class CuisineSelector {

	private static Random random = new Random();
	private static List<Cuisine> allCuisines = Arrays.asList(new Cuisine("Chinese", "./resources/images/chinese.jpg"),
			new Cuisine("Japanese", "./resources/images/japanese.jpg"),
			new Cuisine("Mexican", "./resources/images/mexican.jpg"),
			new Cuisine("Indian", "./resources/images/indian.jpg"), new Cuisine("Thai", "./resources/images/thai.jpg"),
			new Cuisine("Italian", "./resources/images/italian.jpg"),
			new Cuisine("Spanish", "./resources/images/spanish.jpg"),
			new Cuisine("British", "./resources/images/british.jpg"),
			new Cuisine("Korean", "./resources/images/korean.jpg"),
			new Cuisine("Vietnamese", "./resources/images/vietnamese.jpg"));

	/**
	 * Returns a random cuisine from the cuisine list
	 * 
	 * @param currentCuisines : List<Cuisine>
	 * @return cuisine : Cuisine
	 */
	public static Cuisine returnCuisine(List<Cuisine> currentCuisines) {
		int index = random.nextInt(currentCuisines.size());
		return currentCuisines.get(index);
	}

	/**
	 * Removes the given cuisine from the cuisine list and returns the new list
	 * 
	 * @param currentCuisines : List<Cuisine>
	 * @param cuisine         : Cuisine
	 * @return cuisines : List<Cuisine>
	 */
	public static List<Cuisine> removeCuisine(List<Cuisine> currentCuisines, Cuisine cuisine) {
		ArrayList<Cuisine> cuisines = new ArrayList<>();
		cuisines.addAll(currentCuisines);
		for (int i = 0; i < cuisines.size(); i++) {
			if (cuisines.get(i).equals(cuisine))
				cuisines.remove(i);
		}
		return cuisines;
	}

	/**
	 * Returns a list of all the cuisines
	 * 
	 * @return allCuisines : List<Cuisine>
	 */
	public static List<Cuisine> getAllCuisines() {
		return allCuisines;
	}

	/**
	 * Returns a string list of cuisine names, when a list of cuisine objects is
	 * passed in
	 * 
	 * @param cuisines : List<Cuisine>
	 * @return cuisineNames : List<String>
	 */
	public static List<String> getCuisineNames(List<Cuisine> cuisines) {
		List<String> cuisineNames = new ArrayList<>();
		for (Cuisine cuisine : cuisines) {
			cuisineNames.add(cuisine.getName());
		}
		return cuisineNames;
	}

}
