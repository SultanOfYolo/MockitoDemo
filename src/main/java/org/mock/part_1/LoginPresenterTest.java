package org.mock.part_1;

import org.junit.Test;
import org.mockito.Mockito;

public class LoginPresenterTest {

    @Test
    public void testLogin1() {
        Mockito.mock(UserManager.class);    
        LoginPresenter loginPresenter = new LoginPresenter();
        loginPresenter.login("leixu", "leixu password");
        UserManager userManager = loginPresenter.getUserManager();  
        Mockito.verify(userManager).performLogin("leixu", "leixu password");   
       
    }
    
    @Test
    public void testLogin2() throws Exception {
    	LoginPresenter loginPresenter = Mockito.mock(LoginPresenter.class);
        
        loginPresenter.login("leixu", "leixu password");
        //If not defined, all methods of a mock object will return default values
        //int, long type methods will return 0,
        //The boolean method will return false
        //the object method will return null
        //the void method will do nothing.
        Mockito.verify(loginPresenter.getUserManager()).performLogin("leixu", "leixu password");
    }
    
    @Test
    public void testLogin3() throws Exception {
        UserManager mockUserManager = Mockito.mock(UserManager.class);
        LoginPresenter loginPresenter = new LoginPresenter();
        loginPresenter.setUserManager(mockUserManager);  

        loginPresenter.login("leixu", "leixu password");

        Mockito.verify(mockUserManager).performLogin("leixu", "leixu password");
    }
    
}
