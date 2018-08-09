package khj.home.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import khj.home.vo.SmallWays;

@Repository
public class SmallWaysDao {

	@Autowired
	private SqlSession session;
	
	public List<SmallWays> smallWaysList(String big_name) {
		return session.selectList("smallWays.smallWaysList",big_name);
	}

}
