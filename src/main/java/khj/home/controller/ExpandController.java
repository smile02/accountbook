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

import khj.home.service.BigPurposeService;
import khj.home.service.BigWaysService;
import khj.home.service.ExpandService;
import khj.home.service.ExpandServiceImpl;
import khj.home.service.SmallPurposeService;
import khj.home.service.SmallWaysService;
import khj.home.util.Paging;
import khj.home.vo.BigPurpose;
import khj.home.vo.BigWays;
import khj.home.vo.Expand;
import khj.home.vo.Member;

@Controller
public class ExpandController {
	
	@Autowired
	private ExpandService expandService;
	
	@Autowired
	private BigWaysService bigWaysService;
	
	@Autowired
	private BigPurposeService bigPurposeService;
	
	@Autowired
	private SmallWaysService smallWaysService;
	
	@Autowired
	private SmallPurposeService smallPurposeService;

	@Autowired
	private Paging paging;
	
	@RequestMapping(value="/expand", method=RequestMethod.GET)
	public String expandList(Model model,
							@RequestParam(defaultValue="1") int page,
							@RequestParam(required=false) String year,
							@RequestParam(required=false) String month,
							@RequestParam(required=false) String day,
							HttpSession session) {
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(month != null && month.length()<2 && Integer.parseInt(month) >0 && Integer.parseInt(month) < 10 ) {
			month = "0"+month;
		}
		
		String searchParam = "";		
		//전체검색이 아닌 다른 옵션
		if(year != null && !year.equals("")) {
			searchParam = "&year="+year;
		}
		
		if(month != null && !month.equals("")) {
			searchParam = "&month="+month;
		}
		
		model.addAttribute("expandList",expandService.expandList(year,month,day,page,loginMember.getNickname()));
		model.addAttribute("paging", paging.getPaging("/expand",
				page,
				expandService.getTotalCount(year, month, page),
				ExpandServiceImpl.numberOfList,
				ExpandServiceImpl.numberOfPage,
				searchParam));
		//합계에서 닉네임을 보내야 함.
		if(year == null && month == null) {
			model.addAttribute("priceAllSum",expandService.expandPriceSum(year,month,page,loginMember.getNickname()));
		}else if(month == null) {
			model.addAttribute("priceYearSum",expandService.expandPriceSum(year,month,page,loginMember.getNickname()));
		}else {
			model.addAttribute("priceMonthSum",expandService.expandPriceSum(year,month,page,loginMember.getNickname()));
		}
		
		return "/expand/list.jsp";
	}
	
	@RequestMapping(value = "/expand/selectExpand", method=RequestMethod.POST)
	@ResponseBody
	public List<Expand> selectExpand(@RequestParam String nickname,@RequestParam String regdate) {
		List<Expand> expand = expandService.expandList(nickname, regdate);
		
		return expand;		
	}
	
	@RequestMapping(value="/expand/add", method=RequestMethod.GET)
	public String expandAdd(Model model) {
		model.addAttribute("expand",new Expand());
		model.addAttribute("big_waysList",bigWaysList());
		model.addAttribute("big_purposeList",bigPurposeList());
		return "/expand/add.jsp";
	}
	
	@RequestMapping(value = "/expand/add", method=RequestMethod.POST)
	public String expandAdd(@ModelAttribute @Valid Expand expand,BindingResult result
				,Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("expand",expand);
			model.addAttribute("big_waysList",bigWaysList());
			model.addAttribute("big_purposeList",bigPurposeList());
			
			return "/expand/add.jsp";
		}
		Member loginMember = (Member) session.getAttribute("loginMember");
		expand.setNickname(loginMember.getNickname());
		expandService.expandAdd(expand);
		return "redirect:/expand";
	}
	
	private List<BigWays> bigWaysList() {
		List<BigWays> bigWaysList = bigWaysService.big_waysList();
		return bigWaysList;		
	}
	private List<BigPurpose> bigPurposeList(){
		List<BigPurpose> bigPurposeList = bigPurposeService.big_purposeList();
		return bigPurposeList;
	}
	
	@RequestMapping(value = "/expand/mod", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> expandView(@RequestParam int idx,Model model) {
		
		Map<String, Object> expandMap = new HashMap<>();
		Expand expandView = expandService.expandView(idx);
		
		
		expandMap.put("expandView", expandView);
		expandMap.put("bigWaysList", bigWaysList());
		expandMap.put("bigPurposeList", bigPurposeList());
		expandMap.put("smallWaysList", smallWaysService.smallWaysList(expandView.getBig_ways()));
		expandMap.put("smallPurposeList", smallPurposeService.smallPurposeList(expandView.getBig_purpose()));
		
		return expandMap;
	}
	
	@RequestMapping(value="/expand/mod", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> expandMod(@ModelAttribute @Valid Expand expand, BindingResult result){
		Map<String, Object> expandMod = new HashMap<>();
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				System.out.println("메세지 : "+error.getDefaultMessage());
				int index = error.getDefaultMessage().indexOf("->");
				String key = error.getDefaultMessage().substring(0, index);
				String value = error.getDefaultMessage();
				expandMod.put(key, value);
				
			}
			return expandMod;
		}
		
		expandService.expandMod(expand);
		expandMod.put("success", "success");
		return expandMod;
	}
	
	@RequestMapping(value="/expand/del", method=RequestMethod.POST)
	@ResponseBody
	public String expandDel(@RequestParam int idx) {
		if(idx <= 0 || idx != 0) {
			expandService.expandDel(idx);
			return "y";
		}
		return "n";
	}
}
