package projettp2.java;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named
@SessionScoped
public class EntityManagerConfiguration implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static EntityManagerFactory emf;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("modeNone");
		}
		return emf;
	}
}
