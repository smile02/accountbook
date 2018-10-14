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
	public String sendPasswordCode(String email) {
		String from = "lbread2.bread2l@gmail.com";
		String subject = "가계부 임시 비빌번호 발송";
		String tempPassword = makeRandomPassword();
		
		StringBuffer sb = new StringBuffer();
		sb.append("임시 비밀번호 : "+tempPassword);
		sb.append("\n임시비밀번호로 로그인 하신 뒤 마이페이지에서 비밀번호를");
		sb.append("\n변경해주시 바랍니다.");
		
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
			
			helper.setFrom(from);
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(sb.toString());
			
			javaMailSender.send(msg);
		}catch(MessagingException e) {
			e.printStackTrace();
		}
		return tempPassword;
	}
	
	private String makeRandomPassword() {
		StringBuffer sb = new StringBuffer();
		
		String[] passwordArray = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
				"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
				"0","1","2","3","4","5","6","7","8","9"};
		
		int randomArray[] = new int[61];
		for(int i=0; i<8; i++) {
			randomArray[i] = (int)(Math.random() * 61);
		}
		for(int j=0; j<8; j++) {
			sb.append(passwordArray[randomArray[j]]);
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
	
	public Map<String, Object> memberFindMap(String email, String nickname){
		Map<String, Object> memberMap = new HashMap<>();
		
		memberMap.put("email", email);
		memberMap.put("nickname", nickname);
			
		return memberMap;
	}
	
	@Override
	public Member getMember(String email,String nickname) {
		return memberDao.getMember(memberFindMap(email,nickname));
	}

	@Override
	public Member setPassword(String email, String nickname) {
		return memberDao.setPassword(memberFindMap(email, nickname));
	}

	@Override
	public void setPasswordUpdate(String password, String email, String nickname) {
		Map<String, Object> passwordMap = new HashMap<>();
		passwordMap.put("password", password);
		passwordMap.put("email", email);
		passwordMap.put("nickname", nickname);
		memberDao.setPasswordUpdate(passwordMap);
	}

	@Override
	public String getDiffPrice(String nickname, int outYear, String diffMonth) {
		Map<String, Object> diffMap = new HashMap<>();
		diffMap.put("nickname", nickname);
		diffMap.put("year", outYear);
		diffMap.put("month", Integer.parseInt(diffMonth));
		return memberDao.getDiffPrice(diffMap);
	}

}
