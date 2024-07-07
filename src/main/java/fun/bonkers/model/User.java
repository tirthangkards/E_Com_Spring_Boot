package fun.bonkers.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;

	@NotEmpty
	@Column(nullable = false)
	private String firstName;

	@NotEmpty
	@Column(nullable = false)
	private String LastName;

	@Column(nullable = false, unique = true)
	@NotEmpty
	private String email;

	private String password;
	private String city;
	private long phone;
	private String role;

	public User(UUID id, @NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty String email, String password,
			String city, long phone, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		LastName = lastName;
		this.email = email;
		this.password = password;
		this.city = city;
		this.phone = phone;
		this.role = role;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
