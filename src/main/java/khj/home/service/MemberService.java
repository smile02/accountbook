package khj.home.service;

import khj.home.vo.Member;
import khj.home.vo.Memo;

public interface MemberService {

	void memberSignup(Member member);

	Member memberLogin(Member member);

	String memberDualcheck(String id);
	
	boolean memberEmailCheck(String email);

	String sendEmailCode(String email);

	String sendPasswordCode(String email);
	
	String memberPasswordCheck(String nickname, String password);

	void memberInfo(Member member);

	Member getMember(String email, String nickname);

	Member setPassword(String email, String nickname);

	void setPasswordUpdate(String password, String email, String nickname);

	String getDiffPrice(String nickname, int outYear, String diffMonth);

	Memo memoList(String nickname);

	void memoSave(String memo, String nickname);

	void memoAdd(String nickname);

}
