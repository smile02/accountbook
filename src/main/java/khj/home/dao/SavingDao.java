package khj.home.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import khj.home.vo.Saving;
import khj.home.vo.SavingPay;

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

	public List<SavingPay> savingPayList(int idx) {
		return session.selectList("saving.savingPayList",idx);
	}

	public void savingPayAdd(SavingPay savingPay) {
		session.insert("saving.savingPayAdd",savingPay);
	}

	public void savingSumUpdate(Map<String,Object> sumMap) {
		session.update("saving.savingSumUpdate",sumMap);
	}

}
