package khj.home.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import khj.home.vo.Income;

@Repository
public class IncomeDao {

	@Autowired
	private SqlSession session;
	
	public List<Income> incomeList() {
		return session.selectList("income.incomeList");
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

}
