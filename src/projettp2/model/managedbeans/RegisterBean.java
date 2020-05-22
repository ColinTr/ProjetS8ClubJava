package projettp2.model.managedbeans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import projettp2.model.beanentities.Member;
import projettp2.model.services.MemberService;

@Named
@SessionScoped
public class RegisterBean implements Serializable {
	
	private static final long serialVersionUID = -6388510375993857083L;

	private String firstName = "";
    private String lastName = "";
    private String description = "";
    private String email = "";
    private String password = "";
    private String address = "";
    
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String returnAction() {
		Member newMember = new Member();
		
		newMember.setAddress(address);
		newMember.setDescription(description);
		newMember.setEmail(email);
		newMember.setFirstName(firstName);
		newMember.setPassword(password);
		newMember.setLastName(lastName);
		
		if(MemberService.createNewMember(newMember)) {
			return "success";
		}
		else {
			return "failure";
		}
    }
}
