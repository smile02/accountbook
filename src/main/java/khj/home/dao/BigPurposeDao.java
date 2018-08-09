package khj.home.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import khj.home.vo.BigPurpose;

@Repository
public class BigPurposeDao {
	
	@Autowired
	private SqlSession session;

	public List<BigPurpose> big_purposeList() {
		return session.selectList("bigPurpose.bigPurposeList");
	}

}
