package application;

import java.util.Date;

public class JournalVoucher extends AccountManeger{
	
	public JournalVoucher() {
		super();
	}
	
	public JournalVoucher(Date date,String voucher, double value,String item,String type) {
		super(date, voucher, value,item,type);
		setFinalTotal(value);
	}	
	
}
