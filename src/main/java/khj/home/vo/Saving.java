package khj.home.vo;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

public class Saving {

	private int idx;
	private String nickname;
	@Pattern(regexp="[가-힣]{4,10}",message="은행이름을 확인해주세요.")
	private String regbank;
	@Pattern(regexp="[가-힣]{2,16}",message="적금이름을 확인해주세요.")
	private String regname;
	@Null(message="가입날짜를 선택해주세요.")
	private String startreg;
	@Null(message="만기날짜를 선택해주세요.")
	private String endreg;
	private int price;
	private String inputreg;
	
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
	public String getRegbank() {
		return regbank;
	}
	public void setRegbank(String regbank) {
		this.regbank = regbank;
	}
	public String getRegname() {
		return regname;
	}
	public void setRegname(String regname) {
		this.regname = regname;
	}
	public String getStartreg() {
		return startreg;
	}
	public void setStartreg(String startreg) {
		this.startreg = startreg;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getInputreg() {
		return inputreg;
	}
	public void setInputreg(String inputreg) {
		this.inputreg = inputreg;
	}
	public String getEndreg() {
		return endreg;
	}
	public void setEndreg(String endreg) {
		this.endreg = endreg;
	}
	
}
