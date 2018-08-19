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
	public List<Saving> savingList() {
		return savingDao.savingList();
	}

	@Override
	public void savingAdd(Saving saving) {
		savingDao.savingAdd(saving);		
	}

	@Override
	public List<SavingPay> savingPayList(int idx) {
		return savingDao.savingPayList(idx);
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

}
