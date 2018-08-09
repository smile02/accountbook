package khj.home.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import khj.home.vo.BigWays;

@Repository
public class BigWaysDao {

	@Autowired
	private SqlSession session;
	
	public List<BigWays> big_waysList() {
		return session.selectList("bigWays.bigWaysList");
	}

}
