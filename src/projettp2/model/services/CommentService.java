package projettp2.model.services;

import java.util.List;

import javax.persistence.EntityManager;

import projettp2.java.ApplicationConfiguration;
import projettp2.model.beanentities.Comment;
import projettp2.model.beanentities.Member;

public class CommentService {
	
	public static List<Comment> getComments() {
		 EntityManager em = ApplicationConfiguration.getEntityManagerFactory().createEntityManager();
		 List<Comment> returnList = em.createQuery("SELECT c from Comment as c", Comment.class).getResultList();
		 em.close();
		 return returnList;
	}
	
	public static Comment getCommentFromId(int commentId) {
		 EntityManager em = ApplicationConfiguration.getEntityManagerFactory().createEntityManager();
		 Comment returnComment = em.createQuery("SELECT c from Comment as c WHERE c.commentId = :id", Comment.class).setParameter("id", commentId).getResultList().get(0);
		 em.close();
		 return returnComment;
	 }

	public static boolean postNewComment(Comment comment) {
		EntityManager em = ApplicationConfiguration.getEntityManagerFactory().createEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(comment);
			em.getTransaction().commit();
		} catch (Exception e){
			em.getTransaction().rollback();
			em.close();
			return false;
		}

		em.close();
		return true;
	}
	
	public static boolean likeComment(int idComment, String connectedUserLogin) {
		EntityManager em = ApplicationConfiguration.getEntityManagerFactory().createEntityManager();

		em.getTransaction().begin();
		
		Comment commentToLike = em.find(Comment.class, idComment);
		em.merge(commentToLike);
		
		Member memberThatLikes = em.find(Member.class, connectedUserLogin);
		em.merge(memberThatLikes);

		if(commentToLike==null || memberThatLikes==null){
			em.close();
			return false;
		}

		List<Member> memberList = commentToLike.getMembers();
		if(memberList.contains(memberThatLikes)) {
			em.close();
			return false;
		}
		memberList.add(memberThatLikes);
		commentToLike.setMembers(memberList);

		List<Comment> likedComments = memberThatLikes.getLikedComments();
		if(likedComments.contains(commentToLike)) {
			em.close();
			return false;
		}
		likedComments.add(commentToLike);
		memberThatLikes.setLikedComments(likedComments);

		em.getTransaction().commit();
		
		em.close();
		return true;
	}
}
