package khj.home.controller;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import khj.home.service.LoanService;
import khj.home.service.SavingService;
import khj.home.vo.Loan;
import khj.home.vo.LoanPay;
import khj.home.vo.Member;
import khj.home.vo.Saving;

@Controller
public class LoanController {

	@Autowired
	private LoanService loanService;
	
	@Autowired
	private SavingService savingService;
	
	//저장부터 시작
	@RequestMapping(value="/loan/add", method=RequestMethod.POST)
	public String loanAdd (@ModelAttribute @Valid Loan loan, BindingResult result, Model model,
					HttpSession session) {
		Member loginMember = (Member)session.getAttribute("loginMember");		
		Date today = new Date();		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		loan.setLoan_date(date.format(today));
		loan.setNickname(loginMember.getNickname());
		
		if(result.hasErrors()) {
			model.addAttribute("loan",loan);
			model.addAttribute("saving",new Saving());
			model.addAttribute("loanList",loanService.loanList(loginMember.getNickname()));
			model.addAttribute("savingList",savingService.savingList(loginMember.getNickname()));
			return "/saving/list.jsp";
		}
		
		loanService.loanAdd(loan);
		return "redirect:/saving";
	}
	
	@RequestMapping(value="/loan/selectList", method=RequestMethod.POST)
	@ResponseBody
	public List<LoanPay> loanPaySelectList(@RequestParam int idx, HttpSession session){
		Member loginMember = (Member)session.getAttribute("loginMember");
		List<LoanPay> loanPay = loanService.loanPaySelectList(idx, loginMember.getNickname());
		
		return loanPay;
	}
	
	@RequestMapping(value="/loanpay/add", method=RequestMethod.POST)
	public String loanPayAdd(@ModelAttribute LoanPay loanPay, HttpSession session,Model model) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		DecimalFormat df = new DecimalFormat("#,###");
		
		Date today = new Date();		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");		
		loanPay.setInputreg(date.format(today));
		loanPay.setNickname(loginMember.getNickname());
		
		loanPay.setPrice(loanPay.getPrice().replace(",",""));
		loanService.loanPayAdd(loanPay);
		
		Loan newLoan = loanService.loanSelectOne(loanPay.getIdx());
		int savePrice = Integer.parseInt(newLoan.getLoan_price().replace(",", ""));
		int newPrice = Integer.parseInt(loanPay.getPrice().replace(",", ""));
		int resultPrice = savePrice - newPrice;
		if(resultPrice <0) {
			resultPrice = 0;
		}
		
		String returnPrice = df.format(resultPrice);
		loanService.loanPriceUpdate(returnPrice, newLoan.getIdx());
		model.addAttribute("loan", new Loan());
		model.addAttribute("saving", new Saving());
		model.addAttribute("loanList",loanService.loanList(loginMember.getNickname()));
		model.addAttribute("savingList",savingService.savingList(loginMember.getNickname()));
		
		int savingSum = savingService.savingSum(loginMember.getNickname());
		int loanSum = loanService.loanSum(loginMember.getNickname());
		
		model.addAttribute("savingSum", df.format(savingSum));
		model.addAttribute("loanSum", df.format(loanSum));
		
		return "/saving/list.jsp";
	}
	
	@RequestMapping(value="/loanpay/selectOne", method=RequestMethod.POST)
	@ResponseBody
	public LoanPay loanPaySelectOne(@RequestParam int num, HttpSession session) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		LoanPay loanPay = loanService.loanPaySelectOne(num, loginMember.getNickname());
		
		return loanPay;
		
	}
	
	@RequestMapping(value="/loanpay/mod", method=RequestMethod.POST)
	@ResponseBody
	public String loanPayMod(@ModelAttribute LoanPay loanPay, @RequestParam String temp_price, HttpSession session) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		loanPay.setNickname(loginMember.getNickname());
		DecimalFormat df = new DecimalFormat("#,###");
		
		Loan newLoan = loanService.loanSelectOne(loanPay.getIdx());
		
		int savedPrice = Integer.parseInt(newLoan.getLoan_price().replace(",", "")); //대출의 남은 금액
		
		int tempPrice = Integer.parseInt(temp_price.replace(",","")); //변경되기 전의 값
		int price = Integer.parseInt(loanPay.getPrice().replace(",", "")); //내가 입력한값
		String result = "";
		
		if(tempPrice > price) {
			result = df.format(savedPrice + (tempPrice - price));
		}else if(tempPrice < price) {
			result = df.format(savedPrice - (price - tempPrice));
		}else {
			result = newLoan.getLoan_price();
		}		
		
		loanService.loanPriceUpdate(result, loanPay.getIdx());
		loanService.loanPayMod(loanPay);
		return "y";
	}
	
	@RequestMapping(value="/loanpay/del", method=RequestMethod.POST)
	@ResponseBody
	public String loanPayDel(@ModelAttribute LoanPay loanPay, HttpSession session) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		DecimalFormat df = new DecimalFormat("#,###");
		
		loanPay.setNickname(loginMember.getNickname());
		
		int price = Integer.parseInt(loanPay.getPrice().replace(",", ""));
		Loan newLoan = loanService.loanSelectOne(loanPay.getIdx());
		int savedPrice = Integer.parseInt(newLoan.getLoan_price().replace(",", ""));
		
		String result = df.format(savedPrice + price);
		
		loanService.loanPriceUpdate(result, loanPay.getIdx());
		loanService.loanPayDel(loanPay.getNum());
		
		return "y";
	}
	
	@RequestMapping(value = "/loan/mod", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loanMod(@ModelAttribute @Valid Loan loan,BindingResult result, Model model) {
		Map<String, Object> errorMap = new HashMap<>();
		String errString="";
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				errString += error.getDefaultMessage()+"\n";
			}
			errorMap.put("error", errString);
			return errorMap;
		}
		loanService.loanMod(loan);
		errorMap.put("success", "success");
		return errorMap;
	}
	
	@RequestMapping(value = "/loan/del", method=RequestMethod.POST)
	@ResponseBody
	public String loanDel(@RequestParam int idx) {
		loanService.loanDel(idx);
		return "y";
	}
	
}
