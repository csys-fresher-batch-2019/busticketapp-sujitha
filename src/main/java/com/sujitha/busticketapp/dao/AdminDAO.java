package com.sujitha.busticketapp.dao;

public interface AdminDAO {
	//select admin_id from admin where admin_mail='admin@gmail.com'and admin_password='admin1234';
 public int getId(String adminMailId, String adminPassword);
}
