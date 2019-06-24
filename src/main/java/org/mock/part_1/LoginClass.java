package org.mock.part_1;

public class LoginClass {
    private UserManager mUserManager = new UserManager();

    public void login(String username, String password) {
    	//check username und password
        if (username == null || username.length() == 0) return ;
        if (password == null || password.length() < 6) return ;
        
        // If the username and password are correct
        //Call webservice to store user login information
        mUserManager.callWebServiceSaveLoginData(username, password);
    }
    
    public UserManager getUserManager() {   //<==
        return mUserManager;
    }
    
    public void setUserManager(UserManager userManager) {  //<==
        this.mUserManager = userManager;
    }
}
