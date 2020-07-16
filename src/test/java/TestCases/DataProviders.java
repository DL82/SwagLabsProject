package TestCases;

import org.testng.annotations.DataProvider;

public class DataProviders{

	@DataProvider (name="invalidCredentials")
	public static Object[][] invalidCredentials() {
		Object[][] data = { 
				{ "Daniel", "aaaa", "invalid credentials"},
				{ "bbbb", "secret_sauce", "only username is invalid"},
				{ "standard_user", "cccc", "only password is invalid"},
				{ "", "secret_sauce", "username is missing"},
				{ "standard_user", "" ,"password is missing"}
		};
		return data;
	}

	@DataProvider (name="validCredentials")
	public static Object[][] validCredentials() {

		Object[][] data = {{ "standard_user", "secret_sauce", "valid credentials" }};
		return data;
	}
}
