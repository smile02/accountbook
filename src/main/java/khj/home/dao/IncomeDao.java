package khj.home.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import khj.home.vo.Income;

@Repository
public class IncomeDao {

	@Autowired
	private SqlSession session;
	
	public List<Income> incomeList(Map<String, Object> map) {
		return session.selectList("income.incomeList",map);
	}

	public void incomeAdd(Income income) {
		session.insert("income.incomeAdd",income);
	}

	public Income incomeView(int idx) {
		return session.selectOne("income.incomeView",idx);
	}

	public void incomeDel(int idx) {
		session.delete("income.incomeDel",idx);
	}

	public void incomeMod(Income income) {
		session.update("income.incomeMod",income);
	}

	public int getIncomeCount(Map<String, Object> map) {
		return session.selectOne("income.getIncomeCount",map);
	}

	public int incomePriceSum(Map<String, Object> map) {
		return session.selectOne("income.incomePriceSum",map);
	}

	public List<Income> incomeList(String nickname) {
		return session.selectList("income.income",nickname);
	}

	public List<Income> selectIncome(Map<String, String> selectMap) {
		return session.selectList("income.selectIncome",selectMap);
	}

}
