package khj.home.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import khj.home.vo.Expand;

@Repository
public class ExpandDao {
	
	@Autowired
	private SqlSession session;

	public List<Expand> expandList() {
		return session.selectList("expand.expandList");
	}

	public void expandAdd(Expand expand) {
		session.insert("expand.expandAdd",expand);
	}

	public Expand expandView(int idx) {
		return session.selectOne("expand.expandView",idx);
	}

	public void expandMod(Expand expand) {
		session.update("expand.expandMod",expand);
	}

	public void expandDel(int idx) {
		session.delete("expand.expandDel",idx);
	}

}
