package khj.home.service;

import java.util.List;

import khj.home.vo.Saving;
import khj.home.vo.SavingPay;

public interface SavingService {
	public List<Saving> savingList(String nickname);

	public void savingAdd(Saving saving);

	public List<SavingPay> savingPayList(int idx, String nickname);

	public void savingPayAdd(SavingPay savingPay);

	public void savingSumUpdate(int price, int idx);

	public void savingMod(Saving saving);

	public void savingDel(int idx);

	public SavingPay savingPaySelectOne(int num);

	public void savingPayMod(int num, int price);

	public void savingPayDel(int num);
}
