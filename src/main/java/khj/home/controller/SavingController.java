package khj.home.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import khj.home.service.SavingService;
import khj.home.vo.Member;
import khj.home.vo.Saving;

@Controller
public class SavingController {

	@Autowired
	private SavingService savingService;
	
	@RequestMapping(value="/account/saving", method=RequestMethod.GET)
	public String saving(Model model) {
		
		model.addAttribute("savingList",savingService.savingList());
		return "/saving/list.jsp";
	}
	
	@RequestMapping(value="/account/saving/add", method=RequestMethod.POST)
	public String savingAdd(@ModelAttribute Saving saving,HttpSession session) {		
		Member loginMember = (Member)session.getAttribute("loginMember");
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		saving.setInputreg(date.format(today));
		saving.setNickname(loginMember.getNickname());
		savingService.savingAdd(saving);
		return "redirect:/account/saving";
	}
}
