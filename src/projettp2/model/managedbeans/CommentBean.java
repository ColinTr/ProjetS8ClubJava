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

	private List<Comment> comments;
	
	private String commentText;
	
	@Inject
	private LoginBean loginBean;

	public List<Comment> getComments() {
		return CommentService.getComments();
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
		
		System.out.println("trying to like comment...");
		
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
}