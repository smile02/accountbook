package khj.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.home.dao.LoanDao;
import khj.home.vo.Loan;

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

}
