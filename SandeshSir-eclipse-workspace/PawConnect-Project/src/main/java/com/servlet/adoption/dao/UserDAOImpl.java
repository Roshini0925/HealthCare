package com.servlet.adoption.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.servlet.adoption.dto.User;
import com.servlet.adoption.util.DBConnection;


	public class UserDAOImpl implements UserDAO {

	    @Override
	    public boolean registerUser(User user) {
	        String sql= "INSERT INTO users(fullName, email, phone, password) VALUES (?, ?, ?, ?)";

	        try {
	        	Connection con = DBConnection.getConnector();
	             PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1, user.getFullName());
	            ps.setString(2, user.getEmail());
	            ps.setLong(3, user.getPhone());
	            ps.setString(4, user.getPassword());

	            return ps.executeUpdate() > 0;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	
	    @Override
	    public boolean emailExists(String email) {
	        String sql= "SELECT email FROM users WHERE email=?";
	        try {
	        Connection con = DBConnection.getConnector();
	             PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1, email);
	            ResultSet rs = ps.executeQuery();

	            return rs.next(); // email already exists

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

		@Override
		public boolean loginUser(String email, String hashedPassword) {
	
			    String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

			    try (Connection con = DBConnection.getConnector();
			         PreparedStatement ps = con.prepareStatement(sql)) {

			        ps.setString(1, email);
			        ps.setString(2, hashedPassword);

			        ResultSet rs = ps.executeQuery();

			        return rs.next(); // True if match found

			    } catch (Exception e) {
			        e.printStackTrace();
			    }
		
			return false;
		}
	}
