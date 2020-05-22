package projettp2.model.managedbeans;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import projettp2.model.services.MemberService;


@Named /* ("loginBean") */
@SessionScoped // On précise que la portée du bean est limitée à la session en cours
public class LoginBean implements Serializable {

    private static final long serialVersionUID = -5433850275008415405L;
    
    private String login = "";
    private String password = "";
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

	public String returnAction() {
        if(MemberService.validateMember(login, password)) {
        	return "success";
        }
        else {
        	return "failure";
        }
    }
    
    public String cancelAction() {
        System.out.println( "in cancelAction");
        // REDIRIGER VERS UNE AUTRE PAGE ICI
        return "canceling...";
    }
    
    public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    	String inputValue = (String) value;
    	Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    	if( !pattern.matcher(inputValue).matches()) {
    		FacesMessage msg = new FacesMessage("Le login doit être une adresse email");
    		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
    		throw new ValidatorException(msg);
    	}
    }
    
    public void validatePassword(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    	String inputValue = (String) value;
    	Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{4,8}$");
    	if( !pattern.matcher(inputValue).matches()) {
    		FacesMessage msg = new FacesMessage("Votre mot de passe doit contenir entre 4 et 8 caractères non spéciaux");
    		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
    		throw new ValidatorException(msg);
    	}
    }
    
    public String logIn()
    {
    	System.out.println("DANS LOG IN");
    	return "login";
    }
    
    public String logOut()
    {
    	System.out.println("DANS LOG OUT");
    	login = "";
    	password = "";
    	return "logout";
    }
}