package khj.home.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.home.dao.ExpandDao;
import khj.home.vo.Expand;

@Service
public class ExpandServiceImpl implements ExpandService {

	@Autowired
	private ExpandDao expandDao;
	
	public static int numberOfList = 8;
	public static int numberOfPage = 5;

	@Override
	public void expandAdd(Expand expand) {
		expandDao.expandAdd(expand);
	}

	@Override
	public Expand expandView(int idx) {
		return expandDao.expandView(idx);
	}

	@Override
	public void expandMod(Expand expand) {
		expandDao.expandMod(expand);
	}

	@Override
	public void expandDel(int idx) {
		expandDao.expandDel(idx);
	}

	@Override
	public List<Expand> expandList(String year, String month,String day, int page,String nickname) {
		return expandDao.expandList(getSearchMap(year,month,day,page,nickname));
	}
	
	private Map<String, Object> getSearchMap(String year,String month,String day ,int page,String nickname){
		
		int start = (page -1)*numberOfList+1;
		int end = start + numberOfList -1;
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("start", start);
		searchMap.put("end", end);
		searchMap.put("year", year);
		searchMap.put("month", month);
		searchMap.put("day", day);
		searchMap.put("nickname", nickname);
		
		return searchMap;
	}

	@Override
	public int getTotalCount(String year, String month, int page) {
		return expandDao.getTotalCount(getSearchMap(year, month,"", page,""));
	}

	@Override
	public int expandPriceSum(String year, String month, int page) {
		return expandDao.expandPriceSum(getSearchMap(year,month,"",page,""));
	}

	@Override
	public List<Expand> expandList(String string) {
		return expandDao.expandList(string);
	}

	@Override
	public List<Expand> expandList(String nickname, String regdate) {
		Map<String, String> oneMap = new HashMap<>();
		oneMap.put("nickname", nickname);
		oneMap.put("regdate", regdate);
		return expandDao.expandMainList(oneMap);
	}

}
