package com.rslakra.j2eepatterns.antipatterns;

public class LoginManager {
	public boolean doLogin(String username, String password) {
		if (username != null && username.length() > 0 && password != null && password.length() > 2) {
			return true;
		}
		
		return false;
	}
}
