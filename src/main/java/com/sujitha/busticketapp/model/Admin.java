package com.sujitha.busticketapp.model;

public class Admin {
    private int adminId;
    private String adminName;
    private String adminMailId;
    private String adminPassword;
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminMailId() {
		return adminMailId;
	}
	public void setAdminMailId(String adminMailId) {
		this.adminMailId = adminMailId;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminMailId=" + adminMailId
				+ ", adminPassword=" + adminPassword + "]";
	}
    
}
