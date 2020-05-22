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
	
}
