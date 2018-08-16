package khj.home.service;

import java.util.List;

import khj.home.vo.Income;

public interface IncomeService {

	List<Income> incomeList(String year, String month,String day, int page, String nickname);

	void incomeAdd(Income income);

	Income incomeView(int idx);

	void incomeDel(int idx);

	void incomeMod(Income income);

	int getIncomeCount(String year, String month, int page);

	int incomePriceSum(String year, String month, int page);

	List<Income> incomeList(String nickname);

	List<Income> selectIncome(String regdate, String nickname);

}
