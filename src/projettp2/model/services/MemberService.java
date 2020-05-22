package projettp2.model.services;

import javax.persistence.EntityManager;
import java.util.List;

import projettp2.model.beanentities.Member;
import projettp2.java.ApplicationConfiguration;

public class MemberService {
	 
	 public static boolean createNewMember(Member newMember) {
		 EntityManager em = ApplicationConfiguration.getEntityManagerFactory().createEntityManager();
		 
		 if (!em.contains(newMember)){
			 em.getTransaction().begin();
	            try{
	            	em.persist(newMember);
	            	em.getTransaction().commit();
	            } catch (Exception e){
	                e.printStackTrace();
	                em.getTransaction().rollback();
	                return false;
	            }
	        }
		 
		 em.persist(newMember);
		 return true;
	 }
	 
	 public static List<Member> getMembers() {
		 EntityManager em = ApplicationConfiguration.getEntityManagerFactory().createEntityManager();
		 return em.createQuery("SELECT m from Member as m", Member.class).getResultList();
	 }
}
