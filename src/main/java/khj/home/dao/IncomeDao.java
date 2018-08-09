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

}
