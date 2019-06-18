package org.mock.part_2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.mockito.Mockito;

//指定mock对象的某个方法返回特定的值
public class TestReturenValue {
	
	
		@Test
	    public void ThenReturnTest() {
			// create a mock object
			PasswordValidator mockValidator = Mockito.mock(PasswordValidator.class);
	
			//Return true when calling the verifyPassword with "LeiXu_is_handsome" method
			Mockito.when(mockValidator.verifyPassword("LeiXu_is_handsome")).thenReturn(true);
			    
			//Return false when calling the verifyPassword with "LeiXu_is_not_handsome" method
			Mockito.when(mockValidator.verifyPassword("LeiXu_is_not_handsome")).thenReturn(false);
			
			Assert.assertTrue("LeiXu_is_handsome valid",mockValidator.verifyPassword("LeiXu_is_handsome"));
			Assert.assertFalse("LeiXu_is_not_handsome ininvalid",mockValidator.verifyPassword("LeiXu_is_not_handsome"));
			
			
			//Return true when calling the verifyPassword with anyString
		    Mockito.when(mockValidator.verifyPassword(Mockito.anyString())).thenReturn(true);
		    Assert.assertTrue("LeiXu_is_handsome ininvalid",mockValidator.verifyPassword("LeiXu_is_handsome"));
			Assert.assertTrue("LeiXu_is_not_handsome ininvalid",mockValidator.verifyPassword("LeiXu_is_not_handsome"));
		}
	
}
