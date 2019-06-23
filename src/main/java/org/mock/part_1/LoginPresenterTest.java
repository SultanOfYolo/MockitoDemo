package org.mock.part_1;

import org.junit.Test;
import org.mockito.Mockito;

public class LoginPresenterTest {

    
    @Test
    public void testLoginNullException()  {
    	LoginClass loginPresenter = Mockito.mock(LoginClass.class);
        
        loginPresenter.login("leixu", "leixu password");
        //If not defined, all methods of a mock object will return default values
        //int, long type methods will return 0,
        //The boolean method will return false
        //the object method will return null
        //the void method will do nothing.
        Mockito.verify(loginPresenter.getUserManager()).callWebServiceSaveLoginData("leixu", "leixu password");
    }
    
    @Test
    public void testLogin() throws Exception {
        UserManager mockUserManager = Mockito.mock(UserManager.class);
        LoginClass loginPresenter = new LoginClass();
        loginPresenter.setUserManager(mockUserManager);  

        loginPresenter.login("leixu", "leixu password");

        Mockito.verify(loginPresenter.getUserManager()).callWebServiceSaveLoginData("leixu", "leixu password");
    }
    
}
