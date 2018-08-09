package khj.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.home.dao.SmallWaysDao;
import khj.home.vo.SmallWays;

@Service
public class SmallWaysServiceImpl implements SmallWaysService {

	@Autowired
	private SmallWaysDao smallWaysDao;
	
	@Override
	public List<SmallWays> smallWaysList(String big_name) {
		return smallWaysDao.smallWaysList(big_name);
	}

}
