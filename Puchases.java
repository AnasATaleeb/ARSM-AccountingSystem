package application;

import java.util.Date;

public class Puchases extends AccountManeger {
	private double prePaiedTax;
	
	public Puchases() {
		super();
	}

	public Puchases(Date Date, String name, String voucher, String item, long quantity, double value,String type) {
		super(Date,value,name,voucher,type,item,quantity);
		setPrePaiedTax();
	}

	public double getPrePaiedTax() {
		return prePaiedTax;
	}

	public void setPrePaiedTax() {
		this.prePaiedTax = getFinalTotal() - getTotal();
	}
	
	public void setPrePaiedTax(double prePaiedTax) {
		this.prePaiedTax = prePaiedTax;
	}
	
	@Override
	public String toString() {
		return  getDate() + "," + getName() + "," + getVoucher()  +
				"," + getItem() +  "," + getQuantity() + "," + getValue() + "," + getType()+
				"," + prePaiedTax + "," + getTotal()+ "," + getFinalTotal() + "," + getVat();
	}
	
}
