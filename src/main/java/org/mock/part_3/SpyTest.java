package org.mock.part_3;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class SpyTest {
	@Test
	public void testSpy() {
	  //Similar to creating a mock, it just calls the spy method instead of the mock method
	  PasswordValidator spyValidator = spy(PasswordValidator.class);
	  System.out.println(
	  		String.format("[\"Spy Default\"] - spyValidator->verifyPassword(String) invocation count: %d",
					spyValidator.getVerifyPasswordCallCount())
	  );
	  
	  //By default, the spy object will call the real logic of this class 
	  //return the corresponding return value
	  assert  spyValidator.verifyPassword("xiaochuang_is_handsome");
	  assert !spyValidator.verifyPassword("xiaochuang_is_not_handsome");

	  System.out.println(
	  		String.format(
					"[\"Spy Default\"] - spyValidator->verifyPassword(String) invocation count: %d",
					spyValidator.getVerifyPasswordCallCount())
	  );
	  
	  //Can also define behavior for the spy method
	  when(spyValidator.verifyPassword(anyString()))
			  .thenReturn(true);

	  assert spyValidator
			  .verifyPassword("xiaochuang_is_not_handsome");

	  System.out.println(
	  		String.format("[\"when(?).thenReturn(true)\"] - spyValidator->verifyPassword(String) invocation count: %d",
					spyValidator.getVerifyPasswordCallCount())
	  );

	  doReturn(true)
			  .when(spyValidator)
			  .verifyPassword(Mockito.anyString());

	  assert spyValidator
			  .verifyPassword("xiaochuang_is_not_handsome");

	  System.out.println(
	  		String.format(
	  				"[\"doReturn(true).when(?).?\"] - spyValidator->verifyPassword(String) invocation count: %d",
					spyValidator.getVerifyPasswordCallCount())
	  );
	 
	  //restore the default method
	  reset(spyValidator);

	  assert !spyValidator
			  .verifyPassword("xiaochuang_is_not_handsome");
	}
}
