package com.sujitha.busticketapp.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.sujitha.busticketapp.dao.UserDetailsDAO;
import com.sujitha.busticketapp.dao.impl.UserDetailsDAOImpl;
import com.sujitha.busticketapp.model.BusList;

public class TestUser {
	UserDetailsDAO ob = new UserDetailsDAOImpl();

	@Test
	@Ignore
	public void genderCheckTest() throws Exception {
		int userId = 1;
		String expected = "F";
		String actual = ob.getUserGender(userId);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void genderCountTest() throws Exception {
		String gender = "F";
		int expected = 19;
		int actual = ob.getGenderCount(gender);
		Assert.assertEquals(expected, actual);
	}
	
	public void add() {
		BusList obj=new BusList();
		obj.setBusModel(busModel);
		obj.setBusName(busName);
		obj.set
	}

}
