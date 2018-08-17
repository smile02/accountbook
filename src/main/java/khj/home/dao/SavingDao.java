package khj.home.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import khj.home.vo.Saving;

@Repository
public class SavingDao {

	@Autowired
	private SqlSession session;
	
	public List<Saving> savingList() {
		return session.selectList("saving.savingList");
	}

	public void savingAdd(Saving saving) {
		session.insert("saving.savingAdd",saving);
	}

}
