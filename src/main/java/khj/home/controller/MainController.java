package khj.home.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import khj.home.util.AccountCalender;

@Controller
public class MainController {
	
	@Autowired
	private AccountCalender accountCalender;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainPage(Model model) {
		
		Calendar cal = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기
		    
        int year = cal.get(Calendar.YEAR);
        int month = (cal.get(Calendar.MONTH)+1);// 연도와 월을 입력받습니다.
		model.addAttribute("calendar",accountCalender.result(year,month));
		return "/page/main.jsp";
	}
}
