package khj.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import khj.home.service.SavingService;

@Controller
public class SavingController {

	@Autowired
	private SavingService savingService;
	
	@RequestMapping(value="/saving", method=RequestMethod.GET)
	public String saving() {
		return "/saving/list.jsp";
	}
}
