package khj.home.service;

import java.util.List;

import khj.home.vo.Loan;
import khj.home.vo.LoanPay;

public interface LoanService {

	public List<Loan> loanList(String nickname);

	public void loanAdd(Loan loan);

	public List<LoanPay> loanPaySelectList(int idx,String nickname);

	public void loanPayAdd(LoanPay loanPay);

	public Loan loanSelectOne(int idx);

	public void loanPriceUpdate(String returnPrice, int idx);

	public LoanPay loanPaySelectOne(int num, String nickname);

	public void loanPayMod(LoanPay loanPay);

	public void loanPayDel(int num);

	public void loanMod(Loan loan);

	public void loanDel(int idx);
}
