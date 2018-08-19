package khj.home.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import khj.home.service.SavingService;
import khj.home.vo.Member;
import khj.home.vo.Saving;
import khj.home.vo.SavingPay;

@Controller
public class SavingController {

	@Autowired
	private SavingService savingService;
	
	@RequestMapping(value="/saving", method=RequestMethod.GET)
	public String saving(Model model) {
		model.addAttribute("saving",new Saving());
		model.addAttribute("savingList",savingService.savingList());
		return "/saving/list.jsp";
	}
	
	@RequestMapping(value="/saving/add", method=RequestMethod.POST)
	public String savingAdd(@ModelAttribute @Valid Saving saving,BindingResult result,
			HttpSession session,Model model) {		
		Member loginMember = (Member)session.getAttribute("loginMember");		
		Date today = new Date();		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		saving.setInputreg(date.format(today));
		saving.setNickname(loginMember.getNickname());
		
		if(result.hasErrors()) {
			model.addAttribute("saving",saving);
			model.addAttribute("savingList",savingService.savingList());
			return "/saving/list.jsp";
		}
		savingService.savingAdd(saving);
		return "redirect:/saving";
	}
	
	@RequestMapping(value= "/saving/selectList", method=RequestMethod.POST)
	@ResponseBody
	public List<SavingPay> selectList(@RequestParam int idx){
		System.out.println("idx : "+idx);
		List<SavingPay> savingList = savingService.savingPayList(idx);
		
		return savingList;
	}
	
	@RequestMapping(value="/savingpay/add", method=RequestMethod.POST)
	public String savingPayAdd(@ModelAttribute @Valid SavingPay savingPay,
			BindingResult result,HttpSession session,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("savingPay", savingPay);
			model.addAttribute("savingList",savingService.savingList());
			return "/saving/list.jsp";
		}
		Member loginMember = (Member)session.getAttribute("loginMember");		
		Date today = new Date();		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		savingPay.setRegdate(date.format(today));
		savingPay.setNickname(loginMember.getNickname());
		savingService.savingPayAdd(savingPay);
		savingService.savingSumUpdate(savingPay.getPrice(),savingPay.getIdx());
		return "redirect:/saving";
	}
}
