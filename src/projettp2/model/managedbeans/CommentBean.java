package projettp2.model.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import projettp2.model.beanentities.Comment;
import projettp2.model.services.CommentService;

@Named
@RequestScoped
public class CommentBean implements Serializable {

	private static final long serialVersionUID = -4274251529162542806L;

	private List<Comment> comments;

	public List<Comment> getComments() {
		System.out.println("returning comments");
		return CommentService.getComments();
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
}