package projettp2.model.services;

import java.util.ArrayList;
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
	
	public static List<Comment> getTopComments(){
		List<Comment> topCom = new ArrayList<Comment>();
		
		System.out.println("GETTING TOP COMMENTS ...");
		
		List<Comment> allComments = getComments();
		
		if(!allComments.isEmpty())
		{
			for(int i =0 ; i < 3;i++)
			{
				int topLikes = 0;
				Comment topComment = allComments.get(0);
				for(Comment comment : allComments)
				{
					if(comment.getNumberOfLikes() > topLikes)
					{
						topLikes = comment.getNumberOfLikes();
						topComment = comment;
					}
				}
				
				topCom.add(topComment);
				allComments.remove(topComment);
			}
		}
		
		return topCom;
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

		em.getTransaction().commit();
		
		em.close();
		return true;
	}
}
