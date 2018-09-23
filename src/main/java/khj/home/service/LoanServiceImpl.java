package khj.home.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.home.dao.LoanDao;
import khj.home.vo.Loan;
import khj.home.vo.LoanPay;

@Service
public class LoanServiceImpl implements LoanService{

	@Autowired
	private LoanDao loanDao;
	
	@Override
	public List<Loan> loanList(String nickname) {
		return loanDao.loanList(nickname);
	}

	@Override
	public void loanAdd(Loan loan) {
		loanDao.loanAdd(loan);
	}

	@Override
	public List<LoanPay> loanPaySelectList(int idx,String nickname) {
		Map<String, Object> member = new HashMap<>();
		member.put("idx", idx);
		member.put("nickname", nickname);
		return loanDao.loanPaySelectList(member);
	}

	@Override
	public void loanPayAdd(LoanPay loanPay) {
		loanDao.loanPayAdd(loanPay);
	}

	@Override
	public Loan loanSelectOne(int idx) {
		return loanDao.loanSelectOne(idx);
	}

	@Override
	public void loanPriceUpdate(String returnPrice, int idx) {
		Map<String, Object> priceMap = new HashMap<>();
		priceMap.put("idx", idx);
		priceMap.put("loan_price", returnPrice);
		loanDao.loanPriceUpdate(priceMap);
	}

	@Override
	public LoanPay loanPaySelectOne(int num, String nickname) {
		Map<String, Object> loanPayMap = new HashMap<>();
		
		loanPayMap.put("num", num);
		loanPayMap.put("nickname", nickname);
		return loanDao.loanPaySelectOne(loanPayMap);
				
	}

	@Override
	public void loanPayMod(LoanPay loanPay) {
		loanDao.loanPayMod(loanPay);
	}

	@Override
	public void loanPayDel(int num) {
		loanDao.loanPayDel(num);
	}

	@Override
	public void loanMod(Loan loan) {
		loanDao.loanMod(loan);
	}

	@Override
	public void loanDel(int idx) {
		loanDao.loanDel(idx);
	}

}
