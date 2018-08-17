package khj.home.service;

import java.util.List;

import khj.home.vo.Saving;

public interface SavingService {
	public List<Saving> savingList();

	public void savingAdd(Saving saving);
}
