package com.servlet.adoption.dao;

import com.servlet.adoption.dto.User;

	public interface UserDAO {
	    boolean registerUser(User user);
	    boolean emailExists(String email);
	    boolean loginUser(String email, String hashedPassword);

		}
	

	


