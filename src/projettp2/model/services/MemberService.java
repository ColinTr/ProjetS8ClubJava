package projettp2.model.services;

import javax.persistence.EntityManager;
import java.util.List;

import projettp2.model.beanentities.Member;
import projettp2.java.EntityManagerConfiguration;

public class MemberService {
	 
	 public static void createNewMember() {
		 EntityManager em = EntityManagerConfiguration.getEntityManagerFactory().createEntityManager();
		 em.persist(new Member());
	 }
	 
	 public static List<Member> getmembers() {
		 EntityManager em = EntityManagerConfiguration.getEntityManagerFactory().createEntityManager();
		 return em.createQuery("SELECT m from Member as m", Member.class).getResultList();
	 }
}
