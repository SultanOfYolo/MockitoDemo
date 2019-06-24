package org.mock.part_2;

public class LoginClass {
	private PasswordValidate mPasswordValidate =new PasswordValidate();
	public PasswordValidate getmPasswordValidator() {
		return mPasswordValidate;
	}
	public void setmPasswordValidator(PasswordValidate mPasswordValidator) {
		this.mPasswordValidate = mPasswordValidator;
	}
	
	private UserManager mUserManager=new UserManager();
	
	public boolean login( String password,String username) {
	    if (username == null || username.length() == 0) return false;
	    //Check if username and password are correct.
	    boolean isValid= mPasswordValidate.verifyPassword(password,username);
	    if(isValid)
	    {
	    	 mUserManager.callWebServiceSaveLoginData(username, password);
	    }
	    return isValid;
	}
}
