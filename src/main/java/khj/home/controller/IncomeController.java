package khj.home.controller;

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

import khj.home.service.IncomeService;
import khj.home.vo.Income;
import khj.home.vo.Member;

@Controller
public class IncomeController {

	@Autowired
	private IncomeService incomeService;
	
	@RequestMapping(value = "/income", method=RequestMethod.GET)
	public String incomeList(Model model) {
		
		model.addAttribute("incomeList",incomeService.incomeList());
		return "/income/list.jsp";
	}
	
	@RequestMapping(value = "/income/add", method=RequestMethod.GET)
	public String incomeAdd(Model model) {
		model.addAttribute("income",new Income());
		return "/income/add.jsp";
	}
	
	@RequestMapping(value = "/income/add", method=RequestMethod.POST)
	public String incomeAdd(@ModelAttribute @Valid Income income,BindingResult result
			,Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("income", income);
			return "/income/add.jsp";
		}

		Member loginMember = (Member) session.getAttribute("loginMember");
		income.setNickname(loginMember.getNickname());
		incomeService.incomeAdd(income);
		
		return "redirect:/income";
	}
	
	@RequestMapping(value="/income/mod", method=RequestMethod.GET)
	@ResponseBody
	public Income incomeView(@RequestParam int idx) {
		Income incomeView = incomeService.incomeView(idx);
		
		return incomeView;		
	}
	
	@RequestMapping(value="/income/del", method=RequestMethod.POST)
	@ResponseBody
	public String incomeDel(@RequestParam int idx) {
		if(idx != 0 && idx >0) {
			incomeService.incomeDel(idx);
			return "y";
		}
		
		return "n";	
	}
}
