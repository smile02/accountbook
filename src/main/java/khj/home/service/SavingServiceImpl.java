package khj.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.home.dao.SavingDao;
import khj.home.vo.Saving;

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

}
