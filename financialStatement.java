package application;

public class financialStatement extends AccountManeger{
	private String aaccount;
	private double price;
	
	public financialStatement() {
		super();
	}
	
	public financialStatement(String aaccount,double price) {
		super();
		this.aaccount=aaccount;
		this.price=price;
	}

	public String getAaccount() {
		return aaccount;
	}

	public void setAaccount(String aaccount) {
		this.aaccount = aaccount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
