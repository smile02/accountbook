package khj.home.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import khj.home.service.ExpandService;
import khj.home.vo.Expand;
import khj.home.vo.Income;

@Controller
public class ExpandController {
	
	@Autowired
	private ExpandService expandService;

	@RequestMapping(value="/expand", method=RequestMethod.GET)
	public String expandList(Model model) {
		
		model.addAttribute("expandList",expandService.expandList());
		
		return "/expand/list.jsp";
	}
	
	@RequestMapping(value = "/expand/add", method=RequestMethod.POST)
	public String expandAdd(@ModelAttribute @Valid Expand expand,BindingResult result
				,Model model) {
		System.out.println(" 1: "+expand.getRegdate());
		System.out.println(" 2: "+expand.getBig_purpose());
		System.out.println(" 3: "+expand.getBig_ways());
		if(result.hasErrors()) {
			model.addAttribute("expand",expand);
			model.addAttribute("income",new Income());
			return "/page/main.jsp";
		}
		expandService.expandAdd(expand);
		return "redirect:/expand";
	}
}
