package com.servlet.adoption.dao;
	import java.sql.*;
	import java.util.ArrayList;
	import java.util.List;
    import com.servlet.adoption.model.Pet;
    import com.servlet.adoption.util.DBConnection;
    
	public class PetDAOImpl implements PetDAO {

	@Override
	public List<Pet> getAllPets() {
		
		 List<Pet> pets = new ArrayList<>();
	        String query = "SELECT * FROM pets";

	        try (Connection conn = DBConnection.getConnector();
	             PreparedStatement pstmt = conn.prepareStatement(query);
	             ResultSet rs = pstmt.executeQuery()) {

	            while (rs.next()) {
	                Pet pet = new Pet();
	                pet.setId(rs.getInt("id"));
	                pet.setName(rs.getString("name"));
	                pet.setSpecies(rs.getString("species"));
	                pet.setBreed(rs.getString("breed"));
	                pet.setAge(rs.getInt("age"));
	                pet.setGender(rs.getString("gender"));
	                pet.setImageUrl(rs.getString("image_url"));
	                pets.add(pet);
	            }
	            } catch (SQLException e) {
	            	e.printStackTrace();
	            }
	
		return pets;
	}
	}

