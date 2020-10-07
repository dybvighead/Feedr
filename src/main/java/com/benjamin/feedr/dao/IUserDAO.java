package com.benjamin.feedr.dao;

import com.benjamin.feedr.models.User;

public interface IUserDAO {
	
	void addUser(User user);
	User getUserById(int id);
	User getUserByUsername(String username);
	void deleteUser(int id);

}
