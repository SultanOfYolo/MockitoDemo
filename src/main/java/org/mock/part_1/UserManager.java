package org.mock.part_1;

import java.io.IOException;
import java.io.InputStream;

public class UserManager {

	// Remote Service
	public void callWebServiceSaveLoginData(String username, String password) {
		InputStream is = null;

		HttpClient client = new HttpClient();

		PostMethod method = new PostMethod("www.xxx.com/WeatherWSS/Weather.asmx/UserCheckin");

		method.setRequestHeader("Host", "www.xxx.com");

		method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

		method.setParameter("username", username);
		method.setParameter("password", password);
		try {
			client.executeMethod(method);
			is = method.getResponse();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return ;
	}

}
