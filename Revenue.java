package application;
import java.util.*;

public class Revenue extends AccountManeger{
	private double liability;
	
	public Revenue() {
		super();
	}

	public Revenue(Date Date, String name, String voucher, String item, long quantity, double value,String type) {
		super(Date,value,name,voucher,type,item,quantity);
		setLiability();
	}

	public double getLiability() {
		return liability;
	}

	public void setLiability() {
		this.liability = getFinalTotal() - getTotal();
	}
	public void setLiability(double liability) {
		this.liability = liability;
	}

	@Override
	public String toString() {
		return getDate() + "," + getName() + "," + getVoucher()  +
				"," + getItem() +  "," + getQuantity() + "," + getValue() + "," + getType()+
				"," + liability + "," + getTotal()+ "," + getFinalTotal() + "," + getVat();
	}
	
}
