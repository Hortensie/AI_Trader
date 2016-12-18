package pro.xstore.api.sync;

public class Credentials {

	private String login;
	private String password;
	private String appId;
	private String appName;

	public Credentials(String login, String password) {
		this.login = login;
		this.password = password;
	}

	@Deprecated
	public Credentials(long login, String password) {
		this(String.valueOf(login), password);
	}

	public Credentials(String login, String password, String appName) {
		this(login, password);
		this.appName = appName;
	}

	@Deprecated
	public Credentials(long login, String password, String appName) {
		this(String.valueOf(login), password, appName);
	}

	public Credentials(String login, String password, String appId, String appName) {
		this(login, password, appName);
		this.appId = appId;
	}

	@Deprecated
	public Credentials(long login, String password, String appId, String appName) {
		this(String.valueOf(login), password, appId, appName);
	}

	public String getAppId() {
		return appId;
	}

	public String getAppName() {
		return appName;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}	
}