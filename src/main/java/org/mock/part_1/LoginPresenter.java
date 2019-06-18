package org.mock.part_1;

public class LoginPresenter {
    private UserManager mUserManager = new UserManager();

    public void login(String username, String password) {
        if (username == null || username.length() == 0) return;
        if (password == null || password.length() < 6) return;

        mUserManager.performLogin(username, password);
    }
    
    public UserManager getUserManager() {   //<==
        return mUserManager;
    }
    
    public void setUserManager(UserManager userManager) {  //<==
        this.mUserManager = userManager;
    }
}
