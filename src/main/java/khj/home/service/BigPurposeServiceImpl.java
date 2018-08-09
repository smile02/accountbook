package khj.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.home.dao.BigPurposeDao;
import khj.home.vo.BigPurpose;

@Service
public class BigPurposeServiceImpl implements BigPurposeService {

	@Autowired
	private BigPurposeDao bigPurposeDao;
	
	@Override
	public List<BigPurpose> big_purposeList() {
		return bigPurposeDao.big_purposeList();
	}

}
