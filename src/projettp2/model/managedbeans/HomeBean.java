package projettp2.model.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import projettp2.model.beanentities.Member;
import projettp2.model.services.MemberService;


@Named
@RequestScoped 
public class HomeBean implements Serializable {

	private static final long serialVersionUID = 2254864971917028530L;
	
	public List<Member> getMembers() {
		return MemberService.getMembers();
	}
}
