package khj.home.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import khj.home.service.FileService;
import khj.home.service.MemberService;
import khj.home.util.SHA256Encryptor;
import khj.home.vo.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value="/member/signup", method=RequestMethod.GET)
	public String memberSignup(Model model) {
		model.addAttribute("member",new Member());
		return "/member/signup.jsp";
	}
	
	@RequestMapping(value="/member/signup", method=RequestMethod.POST)
	public String memberSignup(@ModelAttribute @Valid Member member,BindingResult result,
							Model model,HttpSession session) {
		System.out.println("회원가입 비번 :"+member.getPassword());
		if(!member.getEmail().equals(
				(String)session.getAttribute("email"))) {
			FieldError error = 
					new FieldError("EmailNotEqualsError", "email", "인증받은 메일로 가입해주세요.");
			//적어주는이름, 변수명(세션에 넣은 변수명), 에러메세지 내용
			result.addError(error);
		}
		
		if(!member.getEmailCode().equals((String)session.getAttribute("emailCode"))) {
			FieldError error = 
					new FieldError("EmailCodeError", "emailCode", "이메일 코드가 일치하지 않습니다.");
			//적어주는이름, 변수명(세션에 넣은 변수명), 에러메세지 내용
			result.addError(error);
		}
		
		if(result.hasErrors()) {
			model.addAttribute("member",member);
			return "/member/signup.jsp";
		}
		String password = SHA256Encryptor.shaEncrypt(member.getPassword());
		member.setPassword(password);
		memberService.memberSignup(member);
		return "redirect:/";
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public String memberLogin(Model model) {
		model.addAttribute("member",new Member());
		return "/member/login.jsp";
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String memberLogin(@ModelAttribute Member member,BindingResult result,
			HttpServletRequest request,Model model) {
	//	System.out.println("로그인 비번 :"+member.getPassword());
		if(memberService.memberDualcheck(member.getNickname()) == null) {
			FieldError error = 
					new FieldError("NicknameInputError", "nickname", "존재하지 않는 아이디입니다.");
			//적어주는이름, 변수명(세션에 넣은 변수명), 에러메세지 내용
			result.addError(error);
		}

		String password = SHA256Encryptor.shaEncrypt(member.getPassword());
		member.setPassword(password);
		
		if(memberService.memberDualcheck(member.getNickname()) != null
				&& memberService.memberPasswordCheck(member.getNickname(),member.getPassword()) == null) {
			FieldError error = 
					new FieldError("PasswordInputError", "password", "비밀번호를 잘못 입력하셨습니다..");
			//적어주는이름, 변수명(세션에 넣은 변수명), 에러메세지 내용
			result.addError(error);
		}
		if(result.hasErrors()) {
			model.addAttribute("member",member);
			return "/member/login.jsp";
		}
		
		Member loginMember = memberService.memberLogin(member);
		request.getSession().setAttribute("loginMember", loginMember);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/member/dualcheck", method=RequestMethod.POST)
	@ResponseBody
	public String memberDualcheck(@RequestParam  String nickname) {
		String memberId = memberService.memberDualcheck(nickname);
		if(memberId != null) {
			return "n";
		}
		return "y";
	}
	
	@RequestMapping(value="/member/emailcheck", method=RequestMethod.POST)
	@ResponseBody
	public String memberEmailCheck(@RequestParam String email, HttpSession session) {
		
		if(email.length() == 0) {
			return "null";
		}
		
		if(!emailValidator(email)) {
			return "incorrect";
		}
		
		if(memberService.memberEmailCheck(email)) {
			return "duplicated";
		}
		
		String emailCode = null;
		try {
			emailCode = memberService.sendEmailCode(email); //			
		}catch(RuntimeException e) {
			return "error";
		}
		
		session.setAttribute("email", email);
		session.setAttribute("emailCode", emailCode);
		
		return "success";
	}
	
	private boolean emailValidator(String email) {
		return Pattern.compile("([A-Za-z0-9]+@[A-Za-z0-9]+.[A-Za-z]{2,10})").matcher(email).matches();
	}
	
	@RequestMapping(value="/member/logout", method=RequestMethod.GET)
	public String memberLogout(HttpSession session) {
		Member member = (Member) session.getAttribute("loginMember");
		if(member != null) {
			session.removeAttribute("loginMember");
			session.invalidate();
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/member/info", method=RequestMethod.GET)
	public String memberInfo() {
		
		return "/member/mypage.jsp";
	}
	
	@RequestMapping(value="/member/info", method=RequestMethod.POST)
	@ResponseBody
	public String memberInfoMod(@ModelAttribute Member member, @RequestParam String noInputEmail, HttpSession session) {
		System.out.println((String)session.getAttribute("email"));
		System.out.println(member.getEmail());
		System.out.println(member.getEmailCode());
		System.out.println(member.getNickname());
		System.out.println(member.getIdx());
		
		if("".equals(member.getNickname())) {
			return "noNickname";
		}
		
		if("".equals(member.getEmail())) {
			return "noEmail";
		}
		
		if("false".equals(member.getEmailCode())) {
			return "noEmailCode";
		}
		
		if(!member.getEmail().equals(
				(String)session.getAttribute("email")) && !"no".equals(noInputEmail)) {
			return "emailCheck";
		}
		
		if(!"no".equals(member.getEmailCode()) &&!"".equals(member.getEmailCode()) && 
				!member.getEmailCode().equals((String)session.getAttribute("emailCode"))) {			
			return "emailCodeCheck";
		}
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(member.getPassword() != null && member.getPassword().length() != 0) {
			if(member.getPassword().length() != 0 || "".equals(member.getPassword())) {
				String password = SHA256Encryptor.shaEncrypt(member.getPassword());
				member.setPassword(password);
			}
		}else {
			
			member.setPassword(loginMember.getPassword());
		}
		
//		System.out.println(member.getPassword());
		
		memberService.memberInfo(member);
		return "y";
	}
	
	@RequestMapping(value="/member/memberfind", method=RequestMethod.GET)
	public String memberFindPage() {
		
		return "/member/find.jsp";
	}
	
	@RequestMapping(value="/member/memberfind", method=RequestMethod.POST)
	@ResponseBody
	public Member memberFindPage(@RequestParam(defaultValue="") String email, @RequestParam(defaultValue="") String nickname) {
//		System.out.println("이메일 : "+email);
		Member resultMember = memberService.getMember(email, nickname);
//		System.out.println(resultMember.getNickname());
//		System.out.println(resultMember.getEmail());
		return resultMember;
	}
	
	@RequestMapping(value="/member/passwordSet", method=RequestMethod.POST)
	@ResponseBody
	public String memberPasswordSet(@RequestParam(defaultValue="") String email, @RequestParam(defaultValue="") String nickname) {
//		System.out.println("이메일 : "+email);
		Member resultMember = memberService.setPassword(email, nickname);
//		System.out.println(resultMember);
		if(resultMember != null) {
			String password = memberService.sendPasswordCode(email);
			memberService.setPasswordUpdate(SHA256Encryptor.shaEncrypt(password), email, nickname);
			System.out.println("1 : "+password);
			System.out.println("2 : "+SHA256Encryptor.shaEncrypt(password));
			
			return "y";
		}
		
		return "n";
//		System.out.println(resultMember.getNickname());
//		System.out.println(resultMember.getEmail());
	}
	
	@RequestMapping(value = "/member/music", method=RequestMethod.GET)
	public String memberMusicManage() {
		return "/member/music.jsp";
	}

}
