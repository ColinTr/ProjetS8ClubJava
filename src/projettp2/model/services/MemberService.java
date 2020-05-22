package projettp2.model.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;

import projettp2.model.beanentities.Member;

@Stateless
public class MemberService {

	 private EntityManager entityManager;
	 
	 public void createNewMember() {
		 entityManager.persist(new Member());
	 }
	 
	 public List<Member> getmembers() {
		 return entityManager.createQuery("SELECT m from Member as m", Member.class).getResultList();
	 }
}
