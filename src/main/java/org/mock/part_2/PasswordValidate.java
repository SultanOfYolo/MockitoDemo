package org.mock.part_2;

public class PasswordValidate {

	public boolean verifyPassword(String password, String Name) {
		// Database connection und query
		return DataBaseManager.CheckUser(Name, password);
	}

}
