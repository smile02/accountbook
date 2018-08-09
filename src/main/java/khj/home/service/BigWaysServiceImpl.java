package khj.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.home.dao.BigWaysDao;
import khj.home.vo.BigWays;

@Service
public class BigWaysServiceImpl implements BigWaysService{

	@Autowired
	private BigWaysDao bigWaysDao;
	
	@Override
	public List<BigWays> big_waysList() {
		return bigWaysDao.big_waysList();
	}

}
