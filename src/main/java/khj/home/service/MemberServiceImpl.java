package khj.home.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import khj.home.dao.MemberDao;
import khj.home.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void memberSignup(Member member) {
		memberDao.memberSignup(member);
	}

	@Override
	public Member memberLogin(Member member) {
		return memberDao.memberLogin(member);
	}

	@Override
	public String memberDualcheck(String id) {
		return memberDao.memberDualcheck(id);
	}

	@Override
	public boolean memberEmailCheck(String email) {
		Member member = memberDao.memberEmailCheck(email);
		return member != null;
	}

	@Override
	public String sendEmailCode(String email) {
		String from = "lbread2.bread2l@gmail.com";
		String subject = "가계부 이메일 인증";
		String emailCode = makeRandomCode();
		
		String content = "인증코드 : "+emailCode;
		
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
			
			helper.setFrom(from);
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(content);
			
			javaMailSender.send(msg);
		}catch(MessagingException e) {
			e.printStackTrace();
		}
		return emailCode;
	}
	
	private String makeRandomCode() {
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<5; i++) {
			sb.append((int)(Math.random() * 10));
		}
		return sb.toString();
	}

	@Override
	public String memberPasswordCheck(String nickname, String password) {
		Map<String, Object> pwdCheckMap = new HashMap<>();
		pwdCheckMap.put("nickname", nickname);
		pwdCheckMap.put("password", password);
		return memberDao.memberPasswordCheck(pwdCheckMap);
	}

	@Override
	public void memberInfo(Member member) {
		memberDao.memberInfo(member);
	}

}
