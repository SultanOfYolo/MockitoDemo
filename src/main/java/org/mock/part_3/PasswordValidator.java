package org.mock.part_3;


import java.util.concurrent.atomic.AtomicInteger;

class PasswordValidator {
	private static final AtomicInteger VERIFY_PASSWORD_COUNTER = new AtomicInteger(0);

	boolean verifyPassword(String password) {
		VERIFY_PASSWORD_COUNTER.incrementAndGet();

		return "xiaochuang_is_handsome".equals(password);
	}

	// final methods are not mock-able!
	final int getVerifyPasswordCallCount() {
	      return VERIFY_PASSWORD_COUNTER.get();
	  }
}
