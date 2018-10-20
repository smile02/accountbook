package khj.home.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import khj.home.vo.Music;

@Repository
public class MusicDao {

	@Autowired
	private SqlSession session;
	
	public void musicAdd(Music music) {
		session.insert("music.musicAdd",music);
	}

}
