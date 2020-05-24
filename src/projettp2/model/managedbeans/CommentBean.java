package projettp2.model.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projettp2.model.beanentities.Comment;
import projettp2.model.services.CommentService;
import projettp2.model.services.MemberService;

@Named
@RequestScoped
public class CommentBean implements Serializable {

	private static final long serialVersionUID = -4274251529162542806L;
	
	private String commentText;
	
	@Inject
	private LoginBean loginBean;

	public List<Comment> getComments() {
		return CommentService.getComments();
	}

	public List<Comment> getTopComments() {
		return CommentService.getTopComments();
	}
	
	public boolean isCommentLikedByMember(int idComment, String emailMember) {
		return CommentService.checkIfCommentIsLikedByMember(idComment, emailMember);
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	public String postCommentAction() {
		Comment newComment = new Comment();
		
		newComment.setText(commentText);
		
		String connectedUserLogin = loginBean.getConnectedUserLogin();
		
		if(connectedUserLogin != null && !connectedUserLogin.isEmpty()) {
			newComment.setMember(MemberService.getMemberFromLogin(connectedUserLogin));
		}
		
		if(CommentService.postNewComment(newComment)) {
			commentText = "";
			return "success";
		}
		else {
			return "failure";
		}
    }
	
	public String like(int idComment) {
		String connectedUserLogin = loginBean.getConnectedUserLogin();
		
		if(connectedUserLogin == null || connectedUserLogin.isEmpty()) {
			return "failure";
		}
		
		if(CommentService.likeComment(idComment, connectedUserLogin)) {
			return "success";
		}
		else {
			return "failure";
		}
	}
	
	public String unlike(int idComment) {
		String connectedUserLogin = loginBean.getConnectedUserLogin();
		
		if(connectedUserLogin == null || connectedUserLogin.isEmpty()) {
			return "failure";
		}
		
		if(CommentService.unlikeComment(idComment, connectedUserLogin)) {
			return "success";
		}
		else {
			return "failure";
		}
	}
	
	
}