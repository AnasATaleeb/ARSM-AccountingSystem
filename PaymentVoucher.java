package application;

import java.util.Date;

public class PaymentVoucher extends AccountManeger{
	private Date checkdate = new Date();
	private String bankName ,receivedfrom;
	private int checkNumber ;
	private boolean isCash;
	
	public PaymentVoucher() {
	}
	
	public PaymentVoucher(Date date, Date checkdate, String name, String bankName,
			String receivedfrom, double value, int checkNumber, boolean isCash) {
		super(date,value,name );
		this.checkdate = checkdate;
		this.bankName = bankName;
		this.setReceivedfrom(receivedfrom);
		this.checkNumber = checkNumber;
		this.isCash = isCash;
	}


	public PaymentVoucher(Date date, String name, String receivedfrom, double value,boolean isCash){
		super(date,value ,name);
		this.setReceivedfrom(receivedfrom);
		this.isCash = isCash;
	}
	public Date getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(Date checkdate) {
		this.checkdate = checkdate;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getCheckNumber() {
		return checkNumber;
	}
	public void setCheckNumber(int checkNumber) {
		this.checkNumber = checkNumber;
	}
	public boolean isCash() {
		return isCash;
	}
	public void setCash(boolean isCash) {
		this.isCash = isCash;
	}

	@Override
	public String toString() {
		return "PaymentVoucher [checkdate=" + checkdate +", bankName=" + bankName
				+ ", receivedfrom=" + ", checkNumber="
				+ checkNumber + ", isCash=" + isCash + "]";
	}

	public String getReceivedfrom() {
		return receivedfrom;
	}

	public void setReceivedfrom(String receivedfrom) {
		this.receivedfrom = receivedfrom;
	}
	
	
}
