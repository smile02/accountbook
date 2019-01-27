package khj.home.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import khj.home.vo.Loan;
import khj.home.vo.LoanPay;

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

	public List<LoanPay> loanPaySelectList(Map<String, Object> member) {
		return session.selectList("loanPay.loanPaySelectList",member);
	}

	public void loanPayAdd(LoanPay loanPay) {
		session.insert("loanPay.loanPayAdd",loanPay);
	}

	public Loan loanSelectOne(int idx) {
		return session.selectOne("loan.loanSelectOne",idx);
	}

	public void loanPriceUpdate(Map<String, Object> priceMap) {
		session.update("loan.loanPriceUpdate",priceMap);
	}

	public LoanPay loanPaySelectOne(Map<String, Object> loanPayMap) {
		return session.selectOne("loanPay.loanPaySelectOne",loanPayMap);
	}

	public void loanPayMod(LoanPay loanPay) {
		session.update("loanPay.loanPayMod",loanPay);
	}

	public void loanPayDel(int num) {
		session.delete("loanPay.loanPayDel",num);
	}

	public void loanMod(Loan loan) {
		session.update("loan.loanMod",loan);
	}

	public void loanDel(int idx) {
		session.delete("loan.loanDel",idx);
	}

	public int loanSum(String nickname) {
		return session.selectOne("loan.loanSum",nickname);
	}
}
