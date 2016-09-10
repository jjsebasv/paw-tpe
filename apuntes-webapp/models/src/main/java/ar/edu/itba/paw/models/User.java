package ar.edu.itba.paw.models;

public class User {
	private String name;
	private long id;
	
	public User(String name, long id) {
		this.setName(name);
		this.setId(id);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
}
