package com.benjamin.feedr;

import com.benjamin.feedr.dao.UserDAO;
import com.benjamin.feedr.models.User;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CRUDTest {

	@Mock
	private UserDAO userDAOMock;

	@Mock
	private User userMock;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Adding a User")
	void addUser() {
		userDAOMock.addUser(userMock);
	}

	@Test
	@DisplayName("Get user by username")
	void getUser() {
		userDAOMock.getUserByUsername(anyString()); // anyInt(), anyObject()
	}
	
	@Test
	@DisplayName("Deleting a User")
	void deleteUser() {
		userDAOMock.deleteUser(anyInt());
	}

}
