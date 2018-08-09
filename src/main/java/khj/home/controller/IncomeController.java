package khj.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import khj.home.service.IncomeService;
import khj.home.vo.Income;

@Controller
public class IncomeController {

	@Autowired
	private IncomeService incomeService;
	
	@RequestMapping(value = "/income", method=RequestMethod.GET)
	public String incomeList(Model model) {
		
		model.addAttribute("incomeList",incomeService.incomeList());
		return "/income/list.jsp";
	}
	
	@RequestMapping(value = "/income/add", method=RequestMethod.POST)
	public String incomeAdd(@ModelAttribute("loginMember") Income income) {
		incomeService.incomeAdd(income);
		
		return "redirect:/income";
	}
}
