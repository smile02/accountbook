package khj.home.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import khj.home.service.ExpandService;
import khj.home.util.AccountCalender;
import khj.home.vo.Expand;

@Controller
public class MainController {
	
	@Autowired
	private AccountCalender accountCalender;
	
	@Autowired
	private ExpandService expandService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainPage(Model model,@RequestParam(defaultValue="0") int year,
									   @RequestParam(defaultValue="0") int month) {
				
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
		
		List<Expand> expand = expandService.expandList();
		for(Expand e : expand) {
			System.out.println("날짜 : "+e.getRegdate()+", 갯수"+e.getCount());
		}
		
		model.addAttribute("expandReg",expand);
		model.addAttribute("calendar",accountCalender.result(outYear,outMonth,expand));
		return "/page/main.jsp";
	}
}
