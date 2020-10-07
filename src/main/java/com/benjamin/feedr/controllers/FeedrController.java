package com.benjamin.feedr.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.benjamin.feedr.dao.UserDAO;
import com.benjamin.feedr.exceptions.InvalidEmailException;
import com.benjamin.feedr.exceptions.UsernameTakenException;
import com.benjamin.feedr.exceptions.WrongPasswordException;
import com.benjamin.feedr.models.Candidates;
import com.benjamin.feedr.models.Cuisine;
import com.benjamin.feedr.models.CuisineSelector;
import com.benjamin.feedr.models.Restaurant;
import com.benjamin.feedr.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author benjamin
 * @version 1.3.0
 * @since 2020-10-06
 */
@Controller
public class FeedrController {

	private static final String API_KEY = "API_KEY";
	public static final Logger DBLogger = LogManager.getLogger("DBLogging");
	public static final Logger ActLogger = LogManager.getLogger("ActLogging");

	/**
	 * Returns index page
	 * 
	 * @param model : Model
	 * @return index : String
	 */
	@RequestMapping(value = "/")
	public String indexPage(Model model) {
		return "index";
	}

	/**
	 * Returns login page
	 * 
	 * @param model : Model
	 * @return login : String
	 */
	@RequestMapping(value = "/login")
	public String loginPage(Model model) {
		return "login";
	}

	/**
	 * Returns register page
	 * 
	 * @param model : Model
	 * @return register : String
	 */
	@RequestMapping(value = "/register")
	public String registerPage(Model model) {
		return "register";
	}

	/**
	 * Returns registerFail page
	 * 
	 * @param model : Model
	 * @return registerFail : String
	 */
	@RequestMapping(value = "/registerFail")
	public String registerFailPage(Model model) {
		return "registerFail";
	}

	/**
	 * Verifies that entered email is in a valid format, and if the username already
	 * exists in the database. If both conditions are met, adds the user to the
	 * database and goes to login page.
	 * 
	 * @param user : User
	 * @return mv : ModelAndView
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute User user) {
		ModelAndView mv = null;
		UserDAO userDAO = new UserDAO();

		try {
			Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"); // RFC 5322
																									// permitted email
																									// format
			Matcher matcher = pattern.matcher(user.getEmail());
			if (!matcher.matches()) // check if email format is valid
				throw new InvalidEmailException("Entered an invalid email");

			if (userDAO.getUserByUsername(user.getUsername()) != null) // check if username already exists in the
																		// database
				throw new UsernameTakenException("Username already taken");

		} catch (NoResultException e) {
			mv = new ModelAndView("/login");
			userDAO.addUser(user);
			DBLogger.info("User persisted in database: as " + user);
			mv.addObject("user", user);
			ActLogger.info("Created User object with parameters from request as: " + user);

		} catch (Exception e) {
			ActLogger.info("Failed to create User - Exception raised: " + e);
			mv = new ModelAndView("/registerFail");
			mv.addObject("registrationError", e.getMessage());
		}
		return mv;
	}

	/**
	 * Verifies that entered username and password matches the information from the
	 * database, then logs in the user.
	 * 
	 * @param model    : Model
	 * @param request  : HttpServletRequest
	 * @param response : HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/verifyLogin", method = RequestMethod.POST)
	public void verifyLogin(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDAO userDAO = new UserDAO();

		try {
			User user = userDAO.getUserByUsername(username);
			if (password.equals(user.getPassword())) {
				session.setAttribute("current_user", user);
				ActLogger.info("Session attribute created as User: " + user);

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/welcome");
				requestDispatcher.forward(request, response);
			} else {
				throw new WrongPasswordException("Invalid password");
			}
		} catch (Exception e) {
			ActLogger.info("Failed to verify log in - Exception raised: " + e);
			session.setAttribute("loginError", e.getMessage());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/loginFail");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * Returns loginFail page
	 * 
	 * @param model : Model
	 * @return loginFail : String
	 */
	@RequestMapping(value = "/loginFail")
	public String loginFailPage(Model model) {
		return "loginFail";
	}

	/**
	 * Returns welcome page
	 * 
	 * @param model : Model
	 * @return welcome : String
	 */
	@RequestMapping(value = "/welcome")
	public String welcomePage(Model model) {
		return "welcome";
	}

	/**
	 * Returns userSettings page
	 * 
	 * @param model : Model
	 * @return userSettings : String
	 */
	@RequestMapping(value = "/userSettings")
	public String userSettingsPage(Model model) {
		return "userSettings";
	}

	/**
	 * Returns cuisineSelectionPage page
	 * 
	 * @param model : Model
	 * @return cuisineSelectionPage : String
	 */
	@RequestMapping(value = "/cuisineSelectionPage")
	public String cuisineSelectionPage(Model model) {
		return "cuisineSelectionPage";
	}

	/**
	 * Initializes the full list of cuisines and returns the first cuisine
	 * 
	 * @param model    : Model
	 * @param request  : HttpServletRequest
	 * @param response : HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getFirstCuisine")
	public void getFirstCuisine(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Cuisine> cuisines = CuisineSelector.getAllCuisines();
		session.setAttribute("cuisines", cuisines);
		ActLogger.info("Current session's Cuisine List: " + CuisineSelector.getCuisineNames(cuisines));

		Cuisine cuisine = CuisineSelector.returnCuisine(cuisines);
		session.setAttribute("cuisine", cuisine);
		ActLogger.info("Current session's Cuisine: " + cuisine.getName());

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/cuisineSelectionPage");
		requestDispatcher.forward(request, response);
	}

	/**
	 * Removes the rejected cuisine from the cuisine list and returns the next
	 * cuisine
	 * 
	 * @param model    : Model
	 * @param request  : HttpServletRequest
	 * @param response : HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getNextCuisine")
	public void getNextCuisine(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Getting variables from previous session
		HttpSession session = request.getSession();
		List<Cuisine> cuisines = (List<Cuisine>) session.getAttribute("cuisines");
		Cuisine cuisine = (Cuisine) session.getAttribute("cuisine");
		String nextPage = "";

		if (cuisines.size() > 1) {
			// removing rejected cuisine
			cuisines = CuisineSelector.removeCuisine(cuisines, cuisine);
			session.setAttribute("cuisines", cuisines);
			ActLogger.info("Current session's Cuisine List: " + CuisineSelector.getCuisineNames(cuisines));

			// getting new cuisine
			cuisine = CuisineSelector.returnCuisine(cuisines);
			session.setAttribute("cuisine", cuisine);
			ActLogger.info("Current session's Cuisine: " + cuisine.getName());

			nextPage = "/cuisineSelectionPage";
		} else
			nextPage = "/outOfCuisines";

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

	/**
	 * Returns outOfCuisines page
	 * 
	 * @param model : Model
	 * @return outOfCuisines : String
	 */
	@RequestMapping(value = "/outOfCuisines")
	public String outOfCuisinesPage(Model model) {
		ActLogger.info("Cuisine list empty");
		return "outOfCuisines";
	}

	/**
	 * Returns selectedCuisinePage page
	 * 
	 * @param model : Model
	 * @return selectedCuisinePage : String
	 */
	@RequestMapping(value = "/selectedCuisine")
	public String selectedCuisinePage(Model model) {
		return "selectedCuisine";
	}

	/**
	 * Gets list of restaurants from Google Places in Json format, converts them
	 * into a list of restaurant objects, then returns them on the displayRestaurant
	 * page
	 * 
	 * @param model    : Model
	 * @param request  : HttpServletRequest
	 * @param response : HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getRestaurants")
	public void getRestaurants(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String food = ((Cuisine) session.getAttribute("cuisine")).getName();
		ActLogger.info("User selected " + food + " cuisine");

		/*
		 * replace "findplacefromtext" with "textsearch" for more results (paid) if
		 * using "textsearch", replace @JsonProperty "candidates" with "results" {@link
		 * Candidates}
		 */
		URL url = new URL(String.format("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?key=%s&"
				+ "input=%s+restaurants&inputtype=textquery&fields=name,formatted_address,photos&type=restaurant",
				API_KEY, food));

		ObjectMapper objectMapper = new ObjectMapper();
		Candidates candidates = objectMapper.readValue(url, Candidates.class);

		// getting details of restaurant
		for (int i = 0; i < 6 && i < candidates.getCandidateJsonList().size(); i++) {
			HashMap restaurantDetails = (HashMap) candidates.getCandidateJsonList().get(i);
			String restaurantName = (String) restaurantDetails.get("name");
			String restaurantAddress = (String) restaurantDetails.get("formatted_address");
			String restaurantPriceLevel = "-";
			String restaurantRating = "-";
			String restaurantPhoto = "";
			try {

				// getting photo of restaurant
				List photoDetailsList = (List) restaurantDetails.get("photos");
				HashMap photoDetailsHashMap = (HashMap) photoDetailsList.get(0);
				String photoReference = (String) photoDetailsHashMap.get("photo_reference");
				restaurantPhoto = String.format(
						"https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=%s&key=%s",
						photoReference, API_KEY);

				restaurantRating = Double.toString((Double) restaurantDetails.get("rating"));
				restaurantPriceLevel = Integer.toString((Integer) restaurantDetails.get("price_level"));
			} catch (Exception e) {
			}
			Restaurant restaurant = new Restaurant(restaurantName, restaurantAddress, restaurantPhoto,
					restaurantPriceLevel, restaurantRating);
			Candidates.candidateList.add(restaurant);
		}

		session.setAttribute("restaurants", Candidates.candidateList);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/displayRestaurants");
		requestDispatcher.forward(request, response);
	}

	/**
	 * Returns displayRestaurants page
	 * 
	 * @param model : Model
	 * @return displayRestaurants : String
	 */
	@RequestMapping(value = "/displayRestaurants")
	public String displayRestaurantsPage(Model model) {
		return "displayRestaurants";
	}

	/**
	 * Invalidates the session and returns quitApp page
	 * 
	 * @param model    : Model
	 * @param request  : HttpServletRequest
	 * @param response : HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/quitApp")
	public String quitAppPage(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		ActLogger.info("Session invalidated: User quit app");
		return "quitApp";
	}

	/**
	 * Returns confirmDeleteUser page
	 * 
	 * @param model : Model
	 * @return confirmDeleteUser : String
	 */
	@RequestMapping(value = "/confirmDeleteUser")
	public String confirmDeleteUserPage(Model model) {
		return "confirmDeleteUser";
	}

	/**
	 * Deletes User from the database and returns quitApp page
	 * 
	 * @param model    : Model
	 * @param request  : HttpServletRequest
	 * @param response : HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteUser")
	public void deleteUser(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("current_user");
		UserDAO userDAO = new UserDAO();
		userDAO.deleteUser(user.getUserId());
		DBLogger.info("User Object deleted in database: " + user);
		ActLogger.info("User deleted profile: " + user);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/quitApp");
		requestDispatcher.forward(request, response);
	}

}
