package khj.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.home.dao.IncomeDao;
import khj.home.vo.Income;

@Service
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private IncomeDao incomeDao;
	
	@Override
	public List<Income> incomeList() {
		return incomeDao.incomeList();
	}

	@Override
	public void incomeAdd(Income income) {
		incomeDao.incomeAdd(income);		
	}

}
