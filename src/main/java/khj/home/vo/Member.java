package khj.home.vo;

import javax.validation.constraints.Pattern;

public class Member {

	@Pattern(regexp="[a-zA-Z0-9가-힣]{2,12}",message="닉네임은 대소문자, 숫자, 단어만(2~12)글자")
	private String nickname;
	@Pattern(regexp="[a-z0-9]{4,12}",message="아이디는 소문자와 숫자만(4~12)글자")
	private String id;
	@Pattern(regexp="[a-zA-Z0-9]{4,20}",message="비밀번호는 대소문자, 숫자로 4~20글자 사이")
	private String password;
	private String passwordCheck;
	@Pattern(regexp="[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}", message="입력하지 않았거나 잘못된 이메일 형식입니다.")
	private String email;
	private String emailCode;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordCheck() {
		return passwordCheck;
	}
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	public String getEmailCode() {
		return emailCode;
	}
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	
	
}
