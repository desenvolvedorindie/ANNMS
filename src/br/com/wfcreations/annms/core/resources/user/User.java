package br.com.wfcreations.annms.core.resources.user;

public class User {
    
    private String username;
    
    private String password;
    
    private boolean isSuperUser;

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public String toString() {
	return "User [username=" + username + ", password=" + password + "]";
    }

    public boolean isSuperUser() {
	return isSuperUser;
    }

    public void setSuperUser(boolean isSuperUser) {
	this.isSuperUser = isSuperUser;
    }
}
