package khj.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khj.home.dao.ExpandDao;
import khj.home.vo.Expand;

@Service
public class ExpandServiceImpl implements ExpandService {

	@Autowired
	private ExpandDao expandDao;
	
	@Override
	public List<Expand> expandList() {
		return expandDao.expandList();
	}

	@Override
	public void expandAdd(Expand expand) {
		expandDao.expandAdd(expand);
	}

}
