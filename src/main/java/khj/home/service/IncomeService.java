package khj.home.service;

import java.util.List;

import khj.home.vo.Income;

public interface IncomeService {

	List<Income> incomeList();

	void incomeAdd(Income income);

}
