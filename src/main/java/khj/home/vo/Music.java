package khj.home.vo;

import org.springframework.web.multipart.MultipartFile;

public class Music {

	private String nickname;
	private String music;
	private int count;
	private MultipartFile music_file;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public MultipartFile getMusic_file() {
		return music_file;
	}
	public void setMusic_file(MultipartFile music_file) {
		this.music_file = music_file;
	}
	
	
	
	
}
