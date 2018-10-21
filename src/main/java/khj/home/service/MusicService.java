package khj.home.service;

import java.util.List;

import khj.home.vo.Music;

public interface MusicService {

	void musicAdd(Music music);

	List<Music> musicList(String nickname, int page);

	int getTotalCount(int page, String nickname);

	List<String> musicMenuList(String nickname);

	

}
