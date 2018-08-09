package khj.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import khj.home.service.BigPurposeService;
import khj.home.service.BigWaysService;
import khj.home.vo.Member;
import khj.home.vo.Expand;
import khj.home.vo.Income;

@Controller
public class MainController {
	
	@Autowired
	private BigWaysService bigWaysService;
	
	@Autowired
	private BigPurposeService bigPurposeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainPage(Model model) {
		model.addAttribute("expand",new Expand());
		model.addAttribute("income",new Income());
		model.addAttribute("big_waysList",bigWaysService.big_waysList());
		model.addAttribute("big_purposeList",bigPurposeService.big_purposeList());
		return "/page/main.jsp";
	}
}
