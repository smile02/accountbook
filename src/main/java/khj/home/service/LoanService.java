package khj.home.service;

import java.util.List;

import khj.home.vo.Loan;

public interface LoanService {

	public List<Loan> loanList(String nickname);
}
