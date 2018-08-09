package khj.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.home.dao.SmallPurposeDao;
import khj.home.vo.SmallPurpose;

@Service
public class SmallPurposeServiceImpl implements SmallPurposeService {

	@Autowired
	private SmallPurposeDao smallPurposeDao;
	
	@Override
	public List<SmallPurpose> smallPurposeList(String big_name) {
		return smallPurposeDao.smallPurposeList(big_name);
	}

}
