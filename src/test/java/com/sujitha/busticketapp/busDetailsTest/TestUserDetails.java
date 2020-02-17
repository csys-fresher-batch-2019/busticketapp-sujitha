package com.sujitha.busticketapp.busDetailsTest;

import java.util.Scanner;

import com.sujitha.busticketapp.DbException;
import com.sujitha.busticketapp.dao.impl.UserDetailsDAOImpl;
import com.sujitha.busticketapp.model.UserDetails;

public class TestUserDetails {

	public static void main(String[] args) throws DbException {
		
		UserDetailsDAOImpl ud = new UserDetailsDAOImpl();
		
		// Add user details:
		       // testInsert();
		
		//Update user mobile number:
		       //ud.updateUserPhnNum(14,9876567890l); 
	    
		// Get usergender:
		      // String gender=ud.getUserGender(12);
	          // System.out.println(gender);
		
	  
		//To fetch the data from database to store txt file:
		      // userdetails();
	
		//ud.login(9876543290l,"suji34");
		Long ph=9876543290l;
		String pass="suji1234";
		int r=ud.getUserId(ph,pass);
		System.out.println(r);
			
	}
	
	
	
	
	
	
	public static void testInsert() throws DbException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the UserName");
		String UserName=sc.next();
		System.out.println("Enter the UserPhnNum");
		long UserPhnNum=sc.nextLong();
		System.out.println("Enter the UserGender");
		String UserGender=sc.next();
		System.out.println("Enter the password");
		String password=sc.next();
		
		UserDetailsDAOImpl ud = new UserDetailsDAOImpl();
       ud.getUserDetails(UserName,UserPhnNum,
    		   UserGender,password) ;
       sc.close();
       
	}
	/*public static void userdetails() throws DbException {
		 ArrayList<UserDetails> list=new ArrayList<UserDetails>();
		 UserDetailsDAOImpl ud = new UserDetailsDAOImpl();
		  list=ud.getUserDetails();
		  String fileContent="";
		  
			  for(UserDetails userdetail:list) {
				  String line=userdetail.toString();
				 // String line = userdetail.userId+","+userdetail.userName+","+userdetail.userPhnNum+","+userdetail.userGender;
				  fileContent= fileContent+line+"\n";
				 
		  }
		System.out.println(fileContent);
		Path path=Paths.get("D:\\UserDetaill_Export).txt");
		Files.write(path,fileContent.getBytes());
	}


	// Display count gender details:
        //int count= ud.getGenderCount("F");
       // System.out.println(count);
	
	
	ArrayList<UserDetails> userdetails=new ArrayList<UserDetails>();

	UserDetails ul= new UserDetails();
	ul.userId=17;
	 ul. userName="aarthi";
     ul.userPhnNum=9876545678l;
	 ul.userGender="F";
	 UserDetails u2= new UserDetails();
		u2.userId=18;
		 u2. userName="sri";
	     u2.userPhnNum=8765432345l;
		 u2.userGender="M";
		 UserDetails u3= new UserDetails();
			u3.userId=19;
			 u3. userName="aarathana";
		     u3.userPhnNum=9090876578l;
			 u3.userGender="F";
			 userdetails.add(ul);
			userdetails.add(u2);
			userdetails.add(u3);
		
			 for(UserDetails udt:userdetails) {
				 ud.addUserDetails(udt);
			 System.out.println(udt);
			
			 
			 }
	}*/
	
	//ud.updateUG(13, UserGenderEnum.F);
		//ud.updateUG(13, UserGenderEnum.F);
}
