package khj.home.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import khj.home.vo.Member;

@Repository
public class MemberDao {

	@Autowired
	private SqlSession session;
	
	public void memberSignup(Member member) {
		session.insert("member.memberSignup",member);
	}

	public Member memberLogin(Member member) {
		return session.selectOne("member.memberLogin",member);
	}

	public String memberDualcheck(String id) {
		return session.selectOne("member.memberDualcheck",id);
	}

	public Member memberEmailCheck(String email) {
		return session.selectOne("member.memberEmailCheck",email);
	}

	public String memberPasswordCheck(Map<String, Object> pwdCheckMap) {
		return session.selectOne("member.memberPasswordCheck",pwdCheckMap);
	}

	public void memberInfo(Member member) {
		session.update("member.memberInfo",member);
	}

	public Member getMember(Map<String, Object> memberFindMap) {
		return session.selectOne("member.getMember",memberFindMap);
	}
}
