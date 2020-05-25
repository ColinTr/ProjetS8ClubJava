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
				em.getTransaction().rollback();
				em.close();
				return false;
			}
		}

		em.close();
		return true;
	 }
	 
	 public static boolean validateMember(String login, String password) {
		 EntityManager em = ApplicationConfiguration.getEntityManagerFactory().createEntityManager();
		 List<Member> member =  em.createQuery("SELECT m from Member as m WHERE email = :login AND password = :password", Member.class).setParameter("login", login).setParameter("password", password).getResultList();
		 
		 if(!member.isEmpty()) {
			 em.close();
			 return true;
		 }

		em.close();
		return false;
	 }
	 
	 public static List<Member> getMembers() {
		 EntityManager em = ApplicationConfiguration.getEntityManagerFactory().createEntityManager();
		 List<Member> m = em.createQuery("SELECT m from Member as m", Member.class).getResultList();
		 em.close();
		 return m;
	 }
	 
	 public static Member getMemberFromLogin(String login) {
		 EntityManager em = ApplicationConfiguration.getEntityManagerFactory().createEntityManager();
		 List<Member> ms = em.createQuery("SELECT m from Member as m WHERE m.email = :login", Member.class).setParameter("login", login).getResultList();
		 em.close();
		 if(ms.isEmpty() || ms.size()==0) {
			 return null;
		 }
		 else {
			 return ms.get(0);
		 }
		 
	 }
}
