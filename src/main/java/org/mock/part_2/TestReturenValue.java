package org.mock.part_2;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


public class TestReturenValue {
	
		@Test
	    public void ThenReturnTest() {
			// create a mock object
			PasswordValidate mockValidator = Mockito.mock(PasswordValidate.class);
		    LoginClass loginPresenter = new LoginClass();
		    loginPresenter.setmPasswordValidator(mockValidator);
			
	
			//Return true when calling the verifyPassword with "Lei_is_handsome" method
			Mockito.when(mockValidator.verifyPassword("Lei_is_handsome", "Lei")).thenReturn(true);
			    
			//Return false when calling the verifyPassword with "Lei_is_not_handsome" method
			Mockito.when(mockValidator.verifyPassword("Lei_is_not_handsome", "Lei")).thenReturn(false);
			
			
			Assert.assertTrue("Lei_is_handsome valid",loginPresenter.login("Lei_is_handsome", "Lei"));
			Assert.assertFalse("Lei_is_not_handsome ininvalid",loginPresenter.login("Lei_is_not_handsome", "Lei"));
			
			//Return true when calling the verifyPassword with anyString
		    Mockito.when(mockValidator.verifyPassword(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
		    Assert.assertTrue("Lei_is_handsome ininvalid",loginPresenter.login("Lei_is_handsome", "Lei"));
			Assert.assertTrue("Lei_is_not_handsome ininvalid",loginPresenter.login("Lei_is_not_handsome", "Lei"));
		}
	
}
