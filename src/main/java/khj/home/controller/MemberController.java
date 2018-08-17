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

import khj.home.service.MemberService;
import khj.home.vo.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/account/member/signup", method=RequestMethod.GET)
	public String memberSignup(Model model) {
		model.addAttribute("member",new Member());
		return "/member/signup.jsp";
	}
	
	@RequestMapping(value="/account/member/signup", method=RequestMethod.POST)
	public String memberSignup(@ModelAttribute @Valid Member member,BindingResult result,
							Model model,HttpSession session) {
		
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
		
		memberService.memberSignup(member);
		return "redirect:account/";
	}
	
	@RequestMapping(value="/account/member/login", method=RequestMethod.GET)
	public String memberLogin(Model model) {
		model.addAttribute("member",new Member());
		return "/member/login.jsp";
	}
	
	@RequestMapping(value="/account/member/login", method=RequestMethod.POST)
	public String memberLogin(@ModelAttribute Member member,BindingResult result,
			HttpServletRequest request,Model model) {
		
		if(memberService.memberDualcheck(member.getNickname()) == null) {
			FieldError error = 
					new FieldError("NicknameInputError", "nickname", "존재하지 않는 아이디입니다.");
			//적어주는이름, 변수명(세션에 넣은 변수명), 에러메세지 내용
			result.addError(error);
		}
		
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
		
		return "redirect:account/";
	}
	
	@RequestMapping(value="/account/member/dualcheck", method=RequestMethod.POST)
	@ResponseBody
	public String memberDualcheck(@RequestParam  String nickname) {
		String memberId = memberService.memberDualcheck(nickname);
		if(memberId != null) {
			return "n";
		}
		return "y";
	}
	
	@RequestMapping(value="/account/member/emailcheck", method=RequestMethod.POST)
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
	
	@RequestMapping(value="/account/member/logout", method=RequestMethod.GET)
	public String memberLogout(HttpSession session) {
		Member member = (Member) session.getAttribute("loginMember");
		if(member != null) {
			session.removeAttribute("loginMember");
			session.invalidate();
		}
		
		return "redirect:account/";
	}
}
