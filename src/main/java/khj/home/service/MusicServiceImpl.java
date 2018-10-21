package khj.home.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.home.dao.MusicDao;
import khj.home.vo.Music;

@Service
public class MusicServiceImpl implements MusicService{

	
	@Autowired
	private MusicDao musicDao;
	
	public static int numberOfList = 10;
	public static int numberOfPage = 5;
	
	@Override
	public void musicAdd(Music music) {
		musicDao.musicAdd(music);
	}

	@Override
	public List<Music> musicList(String nickname, int page) {
		return musicDao.musicSelect(getSearchMap(page,nickname));
	}
	
	private Map<String, Object> getSearchMap(int page,String nickname){
		
		int start = (page -1)*numberOfList+1;
		int end = start + numberOfList -1;
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("start", start);
		searchMap.put("end", end);
		searchMap.put("nickname", nickname);
		
		return searchMap;
	}

	@Override
	public int getTotalCount(int page, String nickname) {
		return musicDao.getTotalCount(getSearchMap(page,nickname));
	}

	@Override
	public List<String> musicMenuList(String nickname) {
		return musicDao.musicMenuList(nickname);
	}

}
