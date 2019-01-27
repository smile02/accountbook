package khj.home.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.home.dao.SavingDao;
import khj.home.vo.Saving;
import khj.home.vo.SavingPay;

@Service
public class SavingServiceImpl implements SavingService {

	@Autowired
	private SavingDao savingDao;
	
	@Override
	public List<Saving> savingList(String nickname) {
		return savingDao.savingList(nickname);
	}

	@Override
	public void savingAdd(Saving saving) {
		savingDao.savingAdd(saving);		
	}

	@Override
	public List<SavingPay> savingPayList(int idx, String nickname) {
		Map<String, Object> savingMap = new HashMap<>();
		savingMap.put("idx", idx);
		savingMap.put("nickname", nickname);
		return savingDao.savingPayList(savingMap);
	}

	@Override
	public void savingPayAdd(SavingPay savingPay) {
		savingDao.savingPayAdd(savingPay);
	}

	@Override
	public void savingSumUpdate(int price, int idx) {
		Map<String, Object> sumMap = new HashMap<>();
		sumMap.put("price", price);
		sumMap.put("idx", idx);
		savingDao.savingSumUpdate(sumMap);
	}

	@Override
	public void savingMod(Saving saving) {
		savingDao.savingMod(saving);
	}

	@Override
	public void savingDel(int idx) {
		savingDao.savingDel(idx);
	}

	@Override
	public SavingPay savingPaySelectOne(int num) {
		return savingDao.savingPaySelectOne(num);
	}

	@Override
	public void savingPayMod(int num, int price) {
		Map<String, Object> payMod = new HashMap<>();
		payMod.put("num", num);
		payMod.put("price", price);
		savingDao.savingPayMod(payMod);
	}

	@Override
	public void savingPayDel(int num) {
		savingDao.savingPayDel(num);
	}

	@Override
	public int savingSum(String nickname) {
		return savingDao.savingSum(nickname);
	}
	
	

}
