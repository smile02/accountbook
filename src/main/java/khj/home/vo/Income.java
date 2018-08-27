package khj.home.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class Income {

	private int idx;
	private String nickname;
	@Size(min=1,message="날짜-> 선택해주세요.")
	private String regdate;
	@Size(min=1, max=20, message="수입내역-> 확인해주세요.")
	private String comments;
	private String ways;
	@Size(min=1, max=300, message="메모-> 1 ~ 300글자 가능합니다.")
	private String memo;
	@Range(min=1, max=999999999, message="금액-> 확인해주세요.")
	private int price;
	private int count;
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
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getWays() {
		return ways;
	}
	public void setWays(String ways) {
		this.ways = ways;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	
	
}
