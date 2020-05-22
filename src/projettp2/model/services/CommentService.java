package projettp2.model.services;

import java.util.List;

import javax.persistence.EntityManager;

import projettp2.java.ApplicationConfiguration;
import projettp2.model.beanentities.Comment;

public class CommentService {
	
	public static List<Comment> getComments() {
		 EntityManager em = ApplicationConfiguration.getEntityManagerFactory().createEntityManager();
		 return em.createQuery("SELECT c from Comment as c", Comment.class).getResultList();
	 }

	public static boolean postNewComment(Comment comment) {
		EntityManager em = ApplicationConfiguration.getEntityManagerFactory().createEntityManager();
		
		em.getTransaction().begin();
		try{
			em.persist(comment);
			em.getTransaction().commit();
		} catch (Exception e){
			em.getTransaction().rollback();
			return false;
		}
		
		return true;
	}
	
}
