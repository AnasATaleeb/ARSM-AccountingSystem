package application;

import java.util.Date;

public class CashRecepts extends AccountManeger{
	private Date checkdate = new Date();
	private String bankName ,receivedfrom,refrance;
	private int checkNumber ;
	private boolean isCash;
	
	public CashRecepts() {
	}
	
	public CashRecepts(Date date, Date checkdate, String name, String bankName,
			String receivedfrom, String refrance, double value, int checkNumber, boolean isCash) {
		super(date,value,name);
		this.checkdate = checkdate;
		this.bankName = bankName;
		this.receivedfrom = receivedfrom;
		this.refrance = refrance;
		this.checkNumber = checkNumber;
		this.isCash = isCash;
	}


	public CashRecepts(Date date, String name, String receivedfrom, String refrance, double value,
			boolean isCash) {
		super(date,value, name );
		this.receivedfrom = receivedfrom;
		this.refrance = refrance;
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
	public String getRefrance() {
		return refrance;
	}
	public void setRefrance(String refrance) {
		this.refrance = refrance;
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
		return "CashRecepts [checkdate=" + checkdate +", bankName=" + bankName
				+ ", receivedfrom=" + receivedfrom + ", refrance=" + refrance + ", checkNumber="
				+ checkNumber + ", isCash=" + isCash + "]";
	}
	
	
}
