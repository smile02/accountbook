package khj.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import khj.home.service.SmallWaysService;
import khj.home.vo.SmallWays;

@Controller
public class WaysController {

	@Autowired
	private SmallWaysService smallWaysService;
	
	@RequestMapping(value="/account/ways/small", method=RequestMethod.POST)
	@ResponseBody
	public List<SmallWays> smallWays(@RequestParam String big_name) {
		return smallWaysService.smallWaysList(big_name);
	}
}
