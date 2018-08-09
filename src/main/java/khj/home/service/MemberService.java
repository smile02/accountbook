package khj.home.service;

import khj.home.vo.Member;

public interface MemberService {

	void memberSignup(Member member);

	Member memberLogin(Member member);

	String memberDualcheck(String id);
	
	boolean memberEmailCheck(String email);

	String sendEmailCode(String email);

	String memberPasswordCheck(String nickname, String password);

}
