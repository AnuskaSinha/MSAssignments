package morganStanley_Day3;

public class User {

	private int id;
	private String userName;
	private String password;

	public User(int id, String userName, String password) {
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String toString() {
		return "id=" + id + ", userName=" + userName + ", password=" + password;
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

}
