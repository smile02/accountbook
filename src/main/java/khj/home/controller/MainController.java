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
import org.springframework.web.bind.annotation.ResponseBody;

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
	//	System.out.println("년 : "+outYear);
	//  System.out.println("월 : "+outMonth);

		
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
		//	System.out.println("수입 금액 : "+incomeSum);
		//	System.out.println("지출 금액 : "+expandSum);
			
			if(incomeSum > expandSum || incomeSum == expandSum) {
				diffPrice = (incomeSum - expandSum)+"";
			}else{
				diffPrice = (-1 * (incomeSum - expandSum))+"";
			}
			
			expand = expandService.expandList(loginMember.getNickname());
			income = incomeService.incomeList(loginMember.getNickname());
		}		
		//System.out.println("차액 : "+diffPrice);
		if(expand != null || income != null) {
			model.addAttribute("calendar",accountCalender.result(outYear,outMonth,expand,income, diffPrice));
		}else {
			model.addAttribute("calendar",accountCalender.result(outYear,outMonth));
		}
		return "/page/main.jsp";
	}
	
	@RequestMapping(value="/next", method = RequestMethod.POST)
	@ResponseBody
	public String nextYearMonth(@RequestParam int year, @RequestParam int month,
					@RequestParam String price, HttpSession session) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember != null) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, month-1,1); //가져온 년, 월을 기준으로 캘린더 셋팅
			//받아온 년, 월을 기준으로 해당월의 마지막일을 가져오기.
//			System.out.println("년 : "+year+", 월 : "+month+", 금액"+price);
//			System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH)); //해당월의 마지막 일
			int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			//위에서 만든 년, 월, 일과 금액으로 지출에 추가.
			Expand diffExpand = new Expand();
			diffExpand.setNickname(loginMember.getNickname());
			diffExpand.setPrice(Integer.parseInt(price.replace(",", "")));
			diffExpand.setBig_purpose("기타");
			diffExpand.setSmall_purpose("이월");
			diffExpand.setBig_ways("카드");
			diffExpand.setSmall_ways("일시불");
			diffExpand.setRegdate(year+"-"+month+"-"+lastDay);
			diffExpand.setComments(month+"월 잔액 이월예정");
			diffExpand.setMemo(month+"월 잔액 이월(지출)");
			expandService.expandAdd(diffExpand); //해당월 마지막날의 지출
			
			/*
			System.out.println("몇월일지 : "+cal.get(Calendar.MONTH));
			System.out.println("몇월일지 : "+(cal.get(Calendar.MONTH)+1));
			System.out.println("몇월일지 : "+(cal.get(Calendar.MONTH)+2)); 
			 */
			//다음달이 내년인 경우를 생각
			//받아온 년, 월을 기준으로 다음달의 첫일을 가져오기.
			int nextMonth = (cal.get(Calendar.MONTH)+2);
			int nextYear = 0;
			if(nextMonth == 12) {
				nextYear = year + 1;
			}else {
				nextYear = year;
			}
			Income diffIncome = new Income();
			diffIncome.setNickname(loginMember.getNickname());
			diffIncome.setPrice(Integer.parseInt(price.replace(",", "")));		
			diffIncome.setWays("카드");
			diffIncome.setRegdate(nextYear+"-"+nextMonth+"-"+1);
			diffIncome.setComments(year+"년 "+month+"월 이월금액");
			diffIncome.setMemo("이월한 금액");
			//위에서 만든 년, 월, 일과 금액으로 수입에 추가.
			incomeService.incomeAdd(diffIncome);
			
			return "y";
		}else {
			return "n";
		}
		
	}
}
