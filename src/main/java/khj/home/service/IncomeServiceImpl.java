package khj.home.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.home.dao.IncomeDao;
import khj.home.vo.Income;

@Service
public class IncomeServiceImpl implements IncomeService {
	
	public static int numberOfList=8;
	public static int numberOfPage=5;
	
	@Autowired
	private IncomeDao incomeDao;
	
	@Override
	public void incomeAdd(Income income) {
		incomeDao.incomeAdd(income);		
	}

	@Override
	public Income incomeView(int idx) {
		return incomeDao.incomeView(idx);
	}

	@Override
	public void incomeDel(int idx) {
		incomeDao.incomeDel(idx);
	}

	@Override
	public void incomeMod(Income income) {
		incomeDao.incomeMod(income);
	}

	@Override
	public int getIncomeCount(String year, String month, int page) {
		return incomeDao.getIncomeCount(getMap(year,month,page));
	}

	@Override
	public List<Income> incomeList(String year, String month, int page) {
		return incomeDao.incomeList(getMap(year,month,page));
	}
	
	private Map<String, Object> getMap(String year, String month, int page){
		Map<String, Object> returnMap = new HashMap<>();
		
		int start = (page -1)*numberOfList+1;
		int end = start + numberOfList -1;
		
		returnMap.put("year", year);
		returnMap.put("month", month);
		returnMap.put("start", start);
		returnMap.put("end", end);
		
		return returnMap;
	}

	@Override
	public int incomePriceSum(String year, String month, int page) {
		return incomeDao.incomePriceSum(getMap(year,month,page));
	}

}
