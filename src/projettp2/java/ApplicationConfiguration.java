package projettp2.java;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
@FacesConfig( version = FacesConfig.Version.JSF_2_3 ) // Activation de CDI
public class ApplicationConfiguration {
	
	private static EntityManagerFactory emf;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("modeNone");
		}
		return emf;
	}
	
}