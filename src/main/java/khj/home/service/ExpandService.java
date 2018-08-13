package khj.home.service;

import java.util.List;

import khj.home.vo.Expand;

public interface ExpandService {

	List<Expand> expandList(String year, String month, int page);

	void expandAdd(Expand expand);

	Expand expandView(int idx);

	void expandMod(Expand expand);

	void expandDel(int idx);

	int getTotalCount(String year, String month, int page);

	int expandPriceSum(String year, String month, int page);

}
