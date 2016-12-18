package pro.xstore.api.sync;

import pro.xstore.api.sync.ServerData.ServerEnum;

public class RunExample {

	//please change the login and password below
	private static String LOGIN = "1000";
	private static String PASSWORD = "changeme";
	
	//please provide the application details if you received them
	private static String APP_ID = null;
	private static String APP_NAME = null;

	public static void main(String[] args) throws Exception {
		Example ex = new Example();
		Credentials credentials = new Credentials(LOGIN, PASSWORD, APP_ID, APP_NAME);
		ex.runExample(ServerEnum.DEMO, credentials);
	}
}