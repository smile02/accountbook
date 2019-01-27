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
	
	public List<Saving> savingList(String nickname) {
		return session.selectList("saving.savingList", nickname);
	}

	public void savingAdd(Saving saving) {
		session.insert("saving.savingAdd",saving);
	}

	public List<SavingPay> savingPayList(Map<String, Object> savingMap) {
		return session.selectList("saving.savingPayList",savingMap);
	}

	public void savingPayAdd(SavingPay savingPay) {
		session.insert("saving.savingPayAdd",savingPay);
	}

	public void savingSumUpdate(Map<String,Object> sumMap) {
		session.update("saving.savingSumUpdate",sumMap);
	}

	public void savingMod(Saving saving) {
		session.update("saving.savingMod",saving);
	}

	public void savingDel(int idx) {
		session.delete("saving.savingDel",idx);
	}

	public SavingPay savingPaySelectOne(int num) {
		return session.selectOne("saving.savingPaySelectOne",num);
	}

	public void savingPayMod(Map<String, Object> payMod) {
		session.update("saving.savingPayMod",payMod);
	}

	public void savingPayDel(int num) {
		session.delete("saving.savingPayDel",num);
	}

	public int savingSum(String nickname) {
		return session.selectOne("saving.savingSum",nickname);
	}

}
