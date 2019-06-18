package org.mock.part_2;

public class LoginPresenter {
	private PasswordValidator mPasswordValidator =new PasswordValidator();
	private UserManager mUserManager=new UserManager();
	public void login(String username, String password) {
	    if (username == null || username.length() == 0) return;
	    //Suppose we have certain requirements for password strength
	    // use a special validator to verify the validity of the password.
	    if (mPasswordValidator.verifyPassword(password)) return; 

	    mUserManager.performLogin(null, password);
	}
}
