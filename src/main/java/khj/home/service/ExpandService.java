package khj.home.service;

import java.util.List;

import khj.home.vo.Expand;

public interface ExpandService {

	List<Expand> expandList();

	void expandAdd(Expand expand);

	Expand expandView(int idx);

	void expandMod(Expand expand);

	void expandDel(int idx);

}
