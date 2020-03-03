package com.sujitha.busticketapp.busDetailsTest;

import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dao.impl.AdminDAOImpl;

public class TestAdmin {
	public static void main(String[] args)throws DbException {
	AdminDAOImpl ai=new AdminDAOImpl();
	int s=ai.getId("admin@gmail.com", "admin1234");
	System.out.println(s);
}
}