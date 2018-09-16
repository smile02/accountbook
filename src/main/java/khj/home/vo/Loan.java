package khj.home.vo;

import javax.validation.constraints.Pattern;

public class Loan {

	private int idx;
	private String nickname;
	@Pattern(regexp="[가-힣]{1,18}", message="단어로 입력해주세요.")
	private String loan_place;
	private String loan_price;
	@Pattern(regexp="[가-힣]{1,18}", message="단어로 입력해주세요.")
	private String loan_purpose;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}", message="대출날짜를 선택해주세요.")
	private String loan_date;
	private String orderdate;
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getLoan_place() {
		return loan_place;
	}
	public void setLoan_place(String loan_place) {
		this.loan_place = loan_place;
	}
	public String getLoan_price() {
		return loan_price;
	}
	public void setLoan_price(String loan_price) {
		this.loan_price = loan_price;
	}
	public String getLoan_purpose() {
		return loan_purpose;
	}
	public void setLoan_purpose(String loan_purpose) {
		this.loan_purpose = loan_purpose;
	}
	public String getLoan_date() {
		return loan_date;
	}
	public void setLoan_date(String loan_date) {
		this.loan_date = loan_date;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	
	
}
