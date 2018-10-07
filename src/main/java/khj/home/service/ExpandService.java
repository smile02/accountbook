package khj.home.service;

import java.util.List;

import khj.home.vo.Expand;

public interface ExpandService {

	List<Expand> expandList(String year, String month,String day, int page, String nickname);

	List<Expand> expandList(String string);
	
	void expandAdd(Expand expand);

	Expand expandView(int idx);

	void expandMod(Expand expand);

	void expandDel(int idx);

	int getTotalCount(String year, String month, int page, String nickname);

	int expandPriceSum(String year, String month, int page, String nickname);

	List<Expand> expandList(String nickname, String regdate);

}
