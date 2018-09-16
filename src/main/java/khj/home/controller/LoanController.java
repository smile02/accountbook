package khj.home.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import khj.home.service.LoanService;
import khj.home.vo.Loan;
import khj.home.vo.Member;

@Controller
public class LoanController {

	@Autowired
	private LoanService loanService;
	
	//저장부터 시작
	@RequestMapping(value="/loan/add", method=RequestMethod.POST)
	public String loanAdd (@ModelAttribute @Valid Loan loan, BindingResult result, Model model,
					HttpSession session) {
		Member loginMember = (Member)session.getAttribute("loginMember");		
		Date today = new Date();		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
	//	loan.setInputreg(date.format(today));
		loan.setNickname(loginMember.getNickname());
		
		if(result.hasErrors()) {
			model.addAttribute("saving",loan);
		//	model.addAttribute("savingList",savingService.savingList(loginMember.getNickname()));
			return "/saving/list.jsp";
		}
		//loanService.savingAdd(loan);
		return "redirect:/saving";
	}
}
