package khj.home.vo;

public class LoanPay {

	private int num;
	private int idx;
	private String nickname;
	private String loan_place;	
	private String loan_purpose;
	private String price;
	private String orderdate;
	private String inputreg;
	private String cmt;
	
	public String getLoan_place() {
		return loan_place;
	}
	public void setLoan_place(String loan_place) {
		this.loan_place = loan_place;
	}
	public String getLoan_purpose() {
		return loan_purpose;
	}
	public void setLoan_purpose(String loan_purpose) {
		this.loan_purpose = loan_purpose;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getInputreg() {
		return inputreg;
	}
	public void setInputreg(String inputreg) {
		this.inputreg = inputreg;
	}
	public String getCmt() {
		return cmt;
	}
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}
}
