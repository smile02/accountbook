package khj.home.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import khj.home.service.SavingService;
import khj.home.vo.Member;
import khj.home.vo.Saving;
import khj.home.vo.SavingPay;

@Controller
public class SavingController {

	@Autowired
	private SavingService savingService;
	
	@RequestMapping(value="/saving", method=RequestMethod.GET)
	public String saving(Model model, HttpSession session) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		model.addAttribute("saving",new Saving());
		model.addAttribute("savingList",savingService.savingList(loginMember.getNickname()));
		return "/saving/list.jsp";
	}
	
	@RequestMapping(value="/saving/add", method=RequestMethod.POST)
	public String savingAdd(@ModelAttribute @Valid Saving saving,BindingResult result,
			HttpSession session,Model model) {		
		Member loginMember = (Member)session.getAttribute("loginMember");		
		Date today = new Date();		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		saving.setInputreg(date.format(today));
		saving.setNickname(loginMember.getNickname());
		
		if(result.hasErrors()) {
			model.addAttribute("saving",saving);
			model.addAttribute("savingList",savingService.savingList(loginMember.getNickname()));
			return "/saving/list.jsp";
		}
		savingService.savingAdd(saving);
		return "redirect:/saving";
	}
	
	@RequestMapping(value= "/saving/selectList", method=RequestMethod.POST)
	@ResponseBody
	public List<SavingPay> selectList(@RequestParam int idx,HttpSession session){
		Member loginMember = (Member)session.getAttribute("loginMember");
		List<SavingPay> savingList = savingService.savingPayList(idx, loginMember.getNickname());
		
		return savingList;
	}
	
	@RequestMapping(value="/savingpay/add", method=RequestMethod.POST)
	@ResponseBody
	public String savingPayAdd(@ModelAttribute SavingPay savingPay,
			HttpSession session,Model model) {
		
		Member loginMember = (Member)session.getAttribute("loginMember");		
		Date today = new Date();		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		savingPay.setRegdate(date.format(today));
		savingPay.setNickname(loginMember.getNickname());
		savingService.savingPayAdd(savingPay);
		savingService.savingSumUpdate(savingPay.getPrice(),savingPay.getIdx());
		return "y";
	}
	
	@RequestMapping(value = "/saving/moddelpage", method=RequestMethod.GET)
	public String savingModDelPage(Model model, HttpSession session) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		model.addAttribute("savingList",savingService.savingList(loginMember.getNickname()));
		
		return "/saving/mod_del_page.jsp";
	}
	
	@RequestMapping(value = "/saving/mod", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> savingMod(@ModelAttribute @Valid Saving saving,BindingResult result, Model model) {
		Map<String, Object> errorMap = new HashMap<>();
		String errString="";
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				errString += error.getDefaultMessage()+"\n";
			}
			errorMap.put("error", errString);
			return errorMap;
		}
		savingService.savingMod(saving);
		errorMap.put("success", "success");
		return errorMap;
	}
	
	@RequestMapping(value = "/saving/del", method=RequestMethod.POST)
	@ResponseBody
	public String savingDel(@RequestParam int idx) {
		savingService.savingDel(idx);
		return "y";
	}
	
	@RequestMapping(value="/savingpay/selectOne", method=RequestMethod.POST)
	@ResponseBody
	public SavingPay savingPaySelectOne(@RequestParam int num) {
		SavingPay savingPay = savingService.savingPaySelectOne(num); 
		return savingPay;
	}
	
	@RequestMapping(value="/savingpay/mod", method=RequestMethod.POST)
	@ResponseBody
	public String savingPayMod(@RequestParam int num, @RequestParam int price, @RequestParam int idx,
								@RequestParam int tempPrice) {
		savingService.savingPayMod(num, price);
		if(price == tempPrice) {
			price = 0;
		}else if(price > tempPrice) {
			price = price - tempPrice;
		}else {
			price = (tempPrice - price) * -1;
		}
		savingService.savingSumUpdate(price,idx);
		return "y";
	}
	
	@RequestMapping(value="/savingpay/del", method=RequestMethod.POST)
	@ResponseBody
	public String savingPayDel(@RequestParam int num,@RequestParam int price,@RequestParam int idx) {
		savingService.savingPayDel(num);
		price = price * (-1);
		savingService.savingSumUpdate(price,idx);
		return "y";
	}
	
}
