package khj.home.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import khj.home.service.ExpandService;
import khj.home.service.IncomeService;
import khj.home.util.AccountCalender;
import khj.home.vo.Expand;
import khj.home.vo.Income;
import khj.home.vo.Member;

@Controller
public class MainController {
	
	@Autowired
	private AccountCalender accountCalender;
	
	@Autowired
	private ExpandService expandService;
	
	@Autowired
	private IncomeService incomeService;
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String mainPage(Model model,@RequestParam(defaultValue="0") int year,
									   @RequestParam(defaultValue="0") int month,
									   HttpSession session) {
		Member loginMember = (Member) session.getAttribute("loginMember");
		Calendar cal = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기
		int outYear = 0;
		int outMonth = 0;
				
		if(year != 0) {
			outYear = year;
		}else {
			outYear = cal.get(Calendar.YEAR);
		}
		if(month != 0) {
			outMonth = month;
		}else {
			outMonth = (cal.get(Calendar.MONTH)+1);// 연도와 월을 입력받습니다.
		}
		List<Expand> expand = null;
		List<Income> income = null;
		//메인의 달력에서 로그인 한 사용자의 지출,수입목록만 보여지게.
		if(loginMember != null) {
			expand = expandService.expandList(loginMember.getNickname());
			income = incomeService.incomeList(loginMember.getNickname());
		}		
		
		if(expand != null || income != null) {
			model.addAttribute("calendar",accountCalender.result(outYear,outMonth,expand,income));
		}else {
			model.addAttribute("calendar",accountCalender.result(outYear,outMonth));
		}
		return "/page/main.jsp";
	}
}
