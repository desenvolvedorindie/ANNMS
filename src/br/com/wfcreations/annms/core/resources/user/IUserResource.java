package br.com.wfcreations.annms.core.resources.user;

public interface IUserResource {

	public void insert(User user);

	public User get(String username);

	public void set(String username, User data);

	public int delete(String username);
}
