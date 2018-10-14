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
import khj.home.service.MemberService;
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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
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
		System.out.println("년 : "+outYear);
		System.out.println("월 : "+outMonth);

		
		List<Expand> expand = null;
		List<Income> income = null;
		String diffPrice = "";
		String diffMonth = "";
		if(outMonth < 10) {
			diffMonth = "0"+outMonth;
		}else {
			diffMonth = outMonth+"";
		}
		int expandSum = 0;
		int incomeSum = 0;
		//메인의 달력에서 로그인 한 사용자의 지출,수입목록만 보여지게.
		if(loginMember != null) {
			//닉네임, 년, 월에 해당하는 수입, 지출의 합계
			expandSum = expandService.expandPriceSum(outYear+"", diffMonth, 0, loginMember.getNickname());
			incomeSum = incomeService.incomePriceSum(outYear+"", diffMonth, 0, loginMember.getNickname());
			System.out.println("수입 금액 : "+incomeSum);
			System.out.println("지출 금액 : "+expandSum);
			
			if(incomeSum > expandSum || incomeSum == expandSum) {
				diffPrice = (incomeSum - expandSum)+"";
			}else{
				diffPrice = (-1 * (incomeSum - expandSum))+"";
			}
			
			expand = expandService.expandList(loginMember.getNickname());
			income = incomeService.incomeList(loginMember.getNickname());
		}		
		System.out.println("차액 : "+diffPrice);
		if(expand != null || income != null) {
			model.addAttribute("calendar",accountCalender.result(outYear,outMonth,expand,income, diffPrice));
		}else {
			model.addAttribute("calendar",accountCalender.result(outYear,outMonth));
		}
		return "/page/main.jsp";
	}
}
