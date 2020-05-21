package projettp2.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Member")
public class Member {

	@Id
	private String email;
	
	private String name;
	private String surname;
	private String address;
	private String password;
	
	@OneToMany(mappedBy="member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
	
	@ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    private List<Comment> likedComments = new ArrayList<>();
	
	public Member() {
		super();
		email = "unknown";
		password = "unknown";
		name = "unknown";
		surname = "unknown";
		address = "unknown";
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return email;
	}
}
