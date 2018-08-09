package khj.home.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class Expand {

	private int idx;
	private String nickname;
	@Size(min=1,message="날짜를 선택해주세요.")
	private String regdate;
	@Size(min=1, max=20, message="지출내역을 확인해주세요.")
	private String comments;
	@Size(min=1,message="방법-대분류를 선택해주세요.")
	private String big_ways;
	@NotNull(message="방법-소분류를 선택해주세요.")
	private String small_ways;
	@Size(min=1,message="목적-추대분류를 선택해주세요.")
	private String big_purpose;
	@NotNull(message="목적-소분류를 선택해주세요.")
	private String small_purpose;
	@Size(min=1, max=300, message="메모는 1 ~ 300글자 가능합니다.")
	private String memo;
	@Range(min=1, max=999999999, message="가격을 확인해주세요.")
	private int price;
	
	
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
	public String getBig_ways() {
		return big_ways;
	}
	public void setBig_ways(String big_ways) {
		this.big_ways = big_ways;
	}
	public String getSmall_ways() {
		return small_ways;
	}
	public void setSmall_ways(String small_ways) {
		this.small_ways = small_ways;
	}
	public String getBig_purpose() {
		return big_purpose;
	}
	public void setBig_purpose(String big_purpose) {
		this.big_purpose = big_purpose;
	}
	public String getSmall_purpose() {
		return small_purpose;
	}
	public void setSmall_purpose(String small_purpose) {
		this.small_purpose = small_purpose;
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
	
	
	
}
