package org.mock.part_3;


	public class PasswordValidator {
		
	  private int _verifyPasswordRufAmount=0;
	  
	  public boolean verifyPassword(String password) {
		  _verifyPasswordRufAmount++;
	      return "xiaochuang_is_handsome".equals(password);
	  }
	  
	  public int GetVerifyPasswordRufAmount() {
	      return _verifyPasswordRufAmount;
	  }
}
