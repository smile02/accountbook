package khj.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.home.dao.MusicDao;
import khj.home.vo.Music;

@Service
public class MusicServiceImpl implements MusicService{

	@Autowired
	private MusicDao musicDao;
	
	@Override
	public void musicAdd(Music music) {
		musicDao.musicAdd(music);
	}

}
