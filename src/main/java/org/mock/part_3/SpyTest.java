package org.mock.part_3;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class SpyTest {
	@Test
	public void testSpy() {
	  //Similar to creating a mock, it just calls the spy method instead of the mock method
	  PasswordValidator spyValidator = Mockito.spy(PasswordValidator.class);
	  System.out.println(spyValidator.GetVerifyPasswordRufAmount());
	  
	  //By default, the spy object will call the real logic of this class 
	  //return the corresponding return value
	  Assert.assertTrue(spyValidator.verifyPassword("xiaochuang_is_handsome")); //true
	  Assert.assertFalse(spyValidator.verifyPassword("xiaochuang_is_not_handsome")); //false
	  System.out.println(spyValidator.GetVerifyPasswordRufAmount());
	  
	  //Can also define behavior for the spy method
	  Mockito.when(spyValidator.verifyPassword(Mockito.anyString())).thenReturn(true);
	  Assert.assertTrue(spyValidator.verifyPassword("xiaochuang_is_not_handsome"));
	  System.out.println("Mockito.when "+ Integer.toString(spyValidator.GetVerifyPasswordRufAmount()));
	  
	  Mockito.doReturn(true).when(spyValidator).verifyPassword(Mockito.anyString());
	  Assert.assertTrue(spyValidator.verifyPassword("xiaochuang_is_not_handsome"));
	  System.out.println("Mockito.doReturn "+ Integer.toString(spyValidator.GetVerifyPasswordRufAmount()));
	 
	  //restore the default method
	  Mockito.reset(spyValidator);
	  Assert.assertFalse(spyValidator.verifyPassword("xiaochuang_is_not_handsome"));
	}
}
