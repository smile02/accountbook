package khj.home.service;

import java.util.List;

import khj.home.vo.Saving;
import khj.home.vo.SavingPay;

public interface SavingService {
	public List<Saving> savingList();

	public void savingAdd(Saving saving);

	public List<SavingPay> savingPayList(int idx);

	public void savingPayAdd(SavingPay savingPay);

	public void savingSumUpdate(int price, int idx);
}
