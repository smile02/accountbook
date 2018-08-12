package khj.home.service;

import java.util.List;

import javax.validation.Valid;

import khj.home.vo.Income;

public interface IncomeService {

	List<Income> incomeList();

	void incomeAdd(Income income);

	Income incomeView(int idx);

	void incomeDel(int idx);

	void incomeMod(Income income);

}
