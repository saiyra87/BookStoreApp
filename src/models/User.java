
package models;

/**
 *
 * @author Pierre
 */
public abstract class User {
    protected String username;
    protected String password;
	
    public User(String username, String password) {
    	this.username = username;
    	this.password = password;
	}
	
        // Getters and Setters
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
}
