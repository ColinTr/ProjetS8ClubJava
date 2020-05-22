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

    private static String loginUserConnected = null;
    
    private String login = "";
    private String password = "";
    
    public String getLogin() {
        System.out.println( "in getLogin" );
        return login;
    }

    public void setLogin(String login) {
        System.out.println( "in setLogin with " + login );
        this.login = login;
    }
    
    public String getPassword() {
        System.out.println( "in getPassword" );
        return password;
    }
    
    public void setPassword(String password) {
        System.out.println( "in setPassword with " + password );
        this.password = password;
    }
    
    public static String getLoginUserConnected(){
    	return LoginBean.loginUserConnected;
    }
    
    public String returnAction() {
    	String validationResult = "";

        boolean valid = MemberService.validateMember(login, password);
        if(valid)
        {
        	validationResult = "success";
        	loginUserConnected = login;
        }
        else
        {
        	validationResult = "failure";
        }
        
        return validationResult;
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
    
}