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
	 
	 public static boolean validateMember(String login, String password) {
		 EntityManager em = ApplicationConfiguration.getEntityManagerFactory().createEntityManager();
		 List<Member> member =  em.createQuery("SELECT m from Member as m WHERE email = :login AND password = :password", Member.class).setParameter("login", login).setParameter("password", password).getResultList();
		 
		 if(!member.isEmpty())
			 return true;
		 
		 return false;
	 
	 }
	 
	 public static List<Member> getMembers() {
		 EntityManager em = ApplicationConfiguration.getEntityManagerFactory().createEntityManager();
		 return em.createQuery("SELECT m from Member as m", Member.class).getResultList();
	 }
}
