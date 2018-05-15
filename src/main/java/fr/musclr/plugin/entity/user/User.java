package fr.musclr.plugin.entity.user;

public class User {
	
	String _id;
	String username;
	String image;
	
	public User(String _id, String username, String image) {
		super();
		this._id = _id;
		this.username = username;
		this.image = image;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "User {_id=" + _id + ", username=" + username + ", image=" + image + "}";
	}

	
	
	
	
	


	
	

}
