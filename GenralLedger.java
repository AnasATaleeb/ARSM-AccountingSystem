package application;

import java.util.ArrayList;
import java.util.Date;

public class GenralLedger extends AccountManeger{
	private double depit,credit;	
	public GenralLedger() {
		super();
	}
	public GenralLedger(Date date,String voucher,String account,double depit,double credit) {
		super(date,voucher,account);
		this.setCredit(credit);
		this.setDepit(depit);
	}

	public double getDepit() {
		return depit;
	}

	public void setDepit(double depit) {
		this.depit = depit;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}
	@Override
	public String toString() {
		return depit + "," + credit + "," + getDate() + ","+ getVoucher() + "," + getAccount();
	}
		
}
