package khj.home.controller;

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

import khj.home.service.IncomeService;
import khj.home.service.IncomeServiceImpl;
import khj.home.util.Paging;
import khj.home.vo.Income;
import khj.home.vo.Member;

@Controller
public class IncomeController {

	@Autowired
	private IncomeService incomeService;
	
	@Autowired
	private Paging paging;
	
	@RequestMapping(value = "/income", method=RequestMethod.GET)
	public String incomeList(Model model,
							@RequestParam(required=false) String year,
							@RequestParam(required=false) String month,
							@RequestParam(required=false) String day,
							@RequestParam(defaultValue="1") int page,
							HttpSession session) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(month != null && month.length()<2 && Integer.parseInt(month) >0 && Integer.parseInt(month) < 10 ) {
			month = "0"+month;
		}
		String searchParam = "";
		if(year != null && !year.equals("")) {
			searchParam = "&year="+year;
		}
		if(month != null && !month.equals("")) {
			searchParam = "&month="+month;
		}
		
		model.addAttribute("incomeList",incomeService.incomeList(year, month, day, page,loginMember.getNickname()));
		model.addAttribute("paging",paging.getPaging("/income",
					page, incomeService.getIncomeCount(year, month, page),
					IncomeServiceImpl.numberOfList,
					IncomeServiceImpl.numberOfPage, 
					searchParam));
		
		if(year == null && month == null) {
			model.addAttribute("priceAllSum",incomeService.incomePriceSum(year,month,page,loginMember.getNickname()));
		}else if(month == null) {
			model.addAttribute("priceYearSum",incomeService.incomePriceSum(year,month,page,loginMember.getNickname()));
		}else {
			model.addAttribute("priceMonthSum",incomeService.incomePriceSum(year,month,page,loginMember.getNickname()));
		}
		
		return "/income/list.jsp";
	}
	
	@RequestMapping(value="/income/selectIncome", method=RequestMethod.POST)
	@ResponseBody
	public List<Income> selectIncome(@RequestParam String regdate, @RequestParam String nickname){
		List<Income> selectIncome = incomeService.selectIncome(regdate, nickname);
		
		return selectIncome;
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
	
	@RequestMapping(value="/income/mod", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> incomeMod(@ModelAttribute @Valid Income income, BindingResult result){
		Map<String, Object> incomeMap = new HashMap<>();
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				int index = error.getDefaultMessage().indexOf("->");
				String key = error.getDefaultMessage().substring(0, index);
				incomeMap.put(key, error.getDefaultMessage());
			}
			return incomeMap;
		}
		
		incomeMap.put("success","success");
		incomeService.incomeMod(income);
		return incomeMap;
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
