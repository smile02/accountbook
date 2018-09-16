package khj.home.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import khj.home.vo.Loan;

@Repository
public class LoanDao {

	@Autowired
	private SqlSession session;
	
	public List<Loan> loanList(String nickname){
		return session.selectList("loan.loanList", nickname);
	}

	public void loanAdd(Loan loan) {
		session.insert("loan.loanAdd",loan);
	}
}
