package khj.home.dao;

import java.util.List;
import java.util.Map;

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

	public List<Music> musicSelect(Map<String, Object> searchMap) {
		return session.selectList("music.musicSelect",searchMap);
	}

	public int getTotalCount(Map<String, Object> searchMap) {
		return session.selectOne("music.getTotalCount",searchMap);
	}

	public List<String> musicMenuList(String nickname) {
		return session.selectList("music.musicMenuList",nickname);
	}

}
