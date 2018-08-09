package khj.home.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import khj.home.vo.SmallPurpose;

@Repository
public class SmallPurposeDao {

	@Autowired
	private SqlSession session;
	
	public List<SmallPurpose> smallPurposeList(String big_name) {
		return session.selectList("smallPurpose.smallPurposeList",big_name);
	}

}
