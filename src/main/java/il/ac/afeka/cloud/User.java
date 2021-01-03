package il.ac.afeka.cloud;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	@Id
	private String email;
	private Name name;
	private String birthdate;
	private String password;
	private Set<String> roles;

	public User(String email) {
		super();
		this.email = email;
	}

	public User(String email, Name name, String birthdate, String password, Set<String> roles) {
		super();
		this.email = email;
		this.name = name;
		this.birthdate = birthdate;
		this.password = password;
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [email=" + email + "]";
	}
	
	
}
