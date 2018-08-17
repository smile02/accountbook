package khj.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import khj.home.service.SmallPurposeService;
import khj.home.vo.SmallPurpose;

@Controller
public class PurposeController {

	@Autowired
	private SmallPurposeService smallPurposeService;
	
	@RequestMapping(value="/account/purpose/small", method=RequestMethod.POST)
	@ResponseBody
	public List<SmallPurpose> smallPurpose(@RequestParam String big_name){
		
		return smallPurposeService.smallPurposeList(big_name);
	}
}
