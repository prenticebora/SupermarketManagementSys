package org.learning.j2ee.supermarket.util;

import org.learning.j2ee.supermarket.dao.User;



public class Session {
	private static User user;		//User对象属性
	public static User getUser() {			//属性的getXXX()方法
		return user;
	}
	public static void setUser( User user) {	//属性的setXXX()方法
		 Session.user = user;	
	}	
}
