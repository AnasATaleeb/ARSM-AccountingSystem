package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AccountManeger implements AccountInt {
	private long quantity;
	private double value,total,finalTotal,vat;
	private Date date = new Date();
	private String name,voucher,type,item,depiteWhthVat,CriditWhthVat,account;
	private ArrayList<Revenue> revenueList = new ArrayList<>();
	private ArrayList<Puchases> puchasesList = new ArrayList<>();
	private ArrayList<CashRecepts> cashReceptsList = new ArrayList<>();
	private ArrayList<JournalVoucher> journalVoucherList = new ArrayList<>();
	private ArrayList<PaymentVoucher> paymentVoucher = new ArrayList<>();
	private ArrayList<GenralLedger> revenueGenral = new ArrayList<GenralLedger>();
	private ArrayList<GenralLedger> purchaseGenral = new ArrayList<GenralLedger>();
	private ArrayList<GenralLedger> cashGenral = new ArrayList<GenralLedger>();
	private ArrayList<GenralLedger> expenseGenral = new ArrayList<GenralLedger>();
	private ArrayList<GenralLedger> ownerGenral = new ArrayList<GenralLedger>();
	private ArrayList<GenralLedger> accountPayableGenral = new ArrayList<GenralLedger>();
	private ArrayList<GenralLedger> accountReceivableGenral = new ArrayList<GenralLedger>();
	private ArrayList<GenralLedger> vatGenral = new ArrayList<GenralLedger>();

	public AccountManeger() {

	}

	public AccountManeger(Date date, double value,String name) {
		this.date=date;
		this.value=value;
		this.name = name;
	}

	public AccountManeger(Date date, String voucher,String account) {
		this.date=date;
		this.voucher= voucher;
		this.account=account;
	}

	public AccountManeger(Date date,String voucher, double value,String item,String type) {
		this.date=date;
		this.value=value;
		this.voucher= voucher;
		this.type=type;
		this.item = item;
		setVat();
		this.total = value;
		setFinalTotal();
	}

	public AccountManeger(Date date, double value,String name,String voucher,String type,String item,Long quantity) {
		this.date=date;
		this.value=value;
		this.name = name;
		this.item=item;
		this.voucher = voucher;
		this.quantity= quantity;
		this.type= type;
		setVat();
		setTotal();
		setFinalTotal();
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public ArrayList<Puchases> getPuchasesList() {
		return puchasesList;
	}

	public void setPuchasesList(ArrayList<Puchases> puchasesList) {
		this.puchasesList = puchasesList;
	}

	public ArrayList<Revenue> getRevenueList() {
		return revenueList;
	}

	public void setRevenueList(ArrayList<Revenue> revenueList) {
		this.revenueList = revenueList;
	}

	public ArrayList<CashRecepts> getCashReceptsList() {
		return cashReceptsList;
	}

	public void setCashReceptsList(ArrayList<CashRecepts> cashReceptsList) {
		this.cashReceptsList = cashReceptsList;
	}

	public double getFinalTotal() {
		return finalTotal;
	}

	public void setFinalTotal(double finalTotal) {
		this.finalTotal = finalTotal;
	}

	public void setVat() {
		this.vat = 0.16;
	}
	public void setVat(double vat) {
		this.vat = vat;
	}

	@Override
	public double allFinalTotalallFinalTotalRevenue() {
		double sum =0.0;
		for (int i = 0; i < revenueList.size(); i++) {
			sum += revenueList.get(i).getFinalTotal();
		}
		return sum;
	}
	@Override
	public double setTotal() {
		this.total = getQuantity() * getValue();
		return total;
	}
	@Override
	public double setFinalTotal() {
		this.finalTotal = getTotal() + ( getTotal() * vat );
		return finalTotal;
	}

	@Override
	public double allFinalTotalallFinalTotalPurchases() {
		double sum =0.0;
		for (int i = 0; i < puchasesList.size(); i++) {
			sum += puchasesList.get(i).getFinalTotal();
		}
		return sum;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getDate() {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		String dateFormatted = fmt.format(date);
		return dateFormatted;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public double getVat() {
		return vat;
	}

	public ArrayList<JournalVoucher> getJournalVoucherList() {
		return journalVoucherList;
	}

	public void setJournalVoucherList(ArrayList<JournalVoucher> journalVoucherList) {
		this.journalVoucherList = journalVoucherList;
	}

	public String getDepiteWhthVat() {
		return depiteWhthVat;
	}

	public void setDepiteWhthVat(String depiteWhthVat) {
		this.depiteWhthVat = depiteWhthVat;
	}

	public String getCriditWhthVat() {
		return CriditWhthVat;
	}

	public void setCriditWhthVat(String criditWhthVat) {
		CriditWhthVat = criditWhthVat;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		if(getItem() == null) {
			this.account = account;
		}
		else
			this.account = account + getItem();
	}
	public void setAccountJournal(String account) {
		this.account = account;
	}

	public ArrayList<PaymentVoucher> getPaymentVoucher() {
		return paymentVoucher;
	}

	public void setPaymentVoucher(ArrayList<PaymentVoucher> paymentVoucher) {
		this.paymentVoucher = paymentVoucher;
	}

	@Override
	public void addRevenueRead(Date date, String name, String voucher, String item, long quantity, double value,
			String type, double prePaiedTax, double total, double finalTotal, double vat) {
		Revenue revenue = new Revenue(date, name, voucher, item, quantity, value, type);
		revenueList.add(revenue);
		revenue.setLiability(prePaiedTax);
		revenue.setTotal(total);
		revenue.setFinalTotal(finalTotal);
		revenue.setVat(vat);
		revenue.ManageRevenue();
	}

	@Override
	public void addPuechsesRead(Date date, String name, String voucher, String item, long quantity, double value,
			String type, double prePaiedTax, double total, double finalTotal, double vat) {
		Puchases purcshases = new Puchases(date, name, voucher, item, quantity, value, type);
		puchasesList.add(purcshases);
		purcshases.setPrePaiedTax(prePaiedTax);
		purcshases.setTotal(total);
		purcshases.setFinalTotal(finalTotal);
		purcshases.setVat(vat); 
		purcshases.ManagePurcahse();

	}
	@Override
	public void ManageRevenue() {
		if(getType().equals("نقداً")) {
			setCriditWhthVat("النقد");
			setDepiteWhthVat(getItem() + "-ضريبة القيمة المضافة");
			setAccount("مبيعات - ");
		}else if(getType().equals("ذمم مدينة")) {
			setCriditWhthVat("ذمم مدينة");
			setDepiteWhthVat(getItem() + "-ضريبة القيمة المضافة");
			setAccount("مبيعات - ");
		}
		else if(getType().equals("Cash")) {
			setCriditWhthVat("Cash");
			setDepiteWhthVat(getItem() + "- VAT");
			setAccount("Revenue -");
		}else if(getType().equals("Account receivable")) {
			setCriditWhthVat("Account receivable");
			setDepiteWhthVat(getItem() + "- VAT");
			setAccount("Revenue -");
		}
	}
	@Override
	public void ManagePurcahse() {
		if(getType().equals("نقداً")) {
			setDepiteWhthVat("النقد");
			setCriditWhthVat(getItem() + "-ضريبة القيمة المضافة");
			setAccount("مشتريات - ");
		}else if(getType().equals("ذمم دائنة")) {
			setDepiteWhthVat("ذمم دائنة");
			setCriditWhthVat(getItem() + "-ضريبة القيمة المضافة");
			setAccount("مشتريات - ");
		}else if(getType().equals("Cash")) {
			setDepiteWhthVat("Cash");
			setCriditWhthVat(getItem() + "- VAT");
			setAccount("Purcahse -");
		}else if(getType().equals("Account payable")) {
			setDepiteWhthVat("Account payable");
			setCriditWhthVat(getItem() + "- VAT");
			setAccount("Purcahse -");
		}
	}
	public ArrayList<GenralLedger> getRevenueGenral() {
		return revenueGenral;
	}

	public void setRevenueGenral(ArrayList<GenralLedger> revenueGenral) {
		this.revenueGenral = revenueGenral;
	}
	public ArrayList<GenralLedger> getPurchaseGenral() {
		return purchaseGenral;
	}
	public void setPurchaseGenral(ArrayList<GenralLedger> purchaseGenral) {
		this.purchaseGenral = purchaseGenral;
	}
	public ArrayList<GenralLedger> getCashGenral() {
		return cashGenral;
	}
	public void setCashGenral(ArrayList<GenralLedger> cashGenral) {
		this.cashGenral = cashGenral;
	}
	public ArrayList<GenralLedger> getExpenseGenral() {
		return expenseGenral;
	}
	public void setExpenseGenral(ArrayList<GenralLedger> expenseGenral) {
		this.expenseGenral = expenseGenral;
	}
	public ArrayList<GenralLedger> getAccountPayableGenral() {
		return accountPayableGenral;
	}
	public void setAccountPayableGenral(ArrayList<GenralLedger> accountPayableGenral) {
		this.accountPayableGenral = accountPayableGenral;
	}
	public ArrayList<GenralLedger> getAccountReceivableGenral() {
		return accountReceivableGenral;
	}
	public void setAccountReceivableGenral(ArrayList<GenralLedger> accountReceivableGenral) {
		this.accountReceivableGenral = accountReceivableGenral;
	}

	public ArrayList<GenralLedger> getVatGenral() {
		return vatGenral;
	}

	public void setVatGenral(ArrayList<GenralLedger> vatGenral) {
		this.vatGenral = vatGenral;
	}

	public ArrayList<GenralLedger> getOwnerGenral() {
		return ownerGenral;
	}

	public void setOwnerGenral(ArrayList<GenralLedger> ownerGenral) {
		this.ownerGenral = ownerGenral;
	}
	@Override
	public double getFinalTotalPayment() {
		double sumDepit =0.0 ,sumCredit = 0.0;
		for (int i = 0; i < expenseGenral.size(); i++) {
			sumDepit +=expenseGenral.get(i).getDepit();
			sumCredit += expenseGenral.get(i).getCredit();
		}
		return  Math.abs(sumDepit - sumCredit);
	}
	@Override
	public double getFinalTotalRevenueForFinStat() {
		double sumDepit =0.0 ,sumCredit = 0.0;
		for (int i = 0; i < revenueGenral.size(); i++) {
			sumDepit +=revenueGenral.get(i).getDepit();
			sumCredit += revenueGenral.get(i).getCredit();
		}
		return  Math.abs(sumDepit - sumCredit);
	}
	@Override
	public double getFinalTotalPurshasesForFinStat() {
		double sumDepit =0.0 ,sumCredit = 0.0;
		for (int i = 0; i < purchaseGenral.size(); i++) {
			sumDepit +=purchaseGenral.get(i).getDepit();
			sumCredit += purchaseGenral.get(i).getCredit();
		}
		return  Math.abs(sumDepit - sumCredit);
	}
	@Override
	public double getFinalTotalOwnerForFinStat() {
		double sumDepit =0.0 ,sumCredit = 0.0;
		for (int i = 0; i < ownerGenral.size(); i++) {
			sumDepit +=ownerGenral.get(i).getDepit();
			sumCredit += ownerGenral.get(i).getCredit();
		}
		return  Math.abs(sumDepit - sumCredit);
	}
	@Override
	public double getFinalTotalCashForFinStat() {
		double sumDepit =0.0 ,sumCredit = 0.0;
		for (int i = 0; i < cashGenral.size(); i++) {
			sumDepit +=cashGenral.get(i).getDepit();
			sumCredit += cashGenral.get(i).getCredit();
		}
		return  Math.abs(sumDepit - sumCredit);
	}
	@Override
	public double getFinalTotalAccPayabelForFinStat() {
		double sumDepit =0.0 ,sumCredit = 0.0;
		for (int i = 0; i <  accountPayableGenral.size(); i++) {
			sumDepit +=accountPayableGenral.get(i).getDepit();
			sumCredit += accountPayableGenral.get(i).getCredit();
		}
		return  Math.abs(sumDepit - sumCredit);
	}
	@Override
	public double getFinalTotalAccRecevForFinStat() {
		double sumDepit =0.0 ,sumCredit = 0.0;
		for (int i = 0; i <  accountReceivableGenral.size(); i++) {
			sumDepit +=accountReceivableGenral.get(i).getDepit();
			sumCredit += accountReceivableGenral.get(i).getCredit();
		}
		return  Math.abs(sumDepit - sumCredit);
	}
	@Override
	public double getFinalTotalVatForFinStat() {
		double sumDepit =0.0 ,sumCredit = 0.0;
		for (int i = 0; i <  vatGenral.size(); i++) {
			sumDepit +=vatGenral.get(i).getDepit();
			sumCredit += vatGenral.get(i).getCredit();
		}
		return  Math.abs(sumDepit - sumCredit);
	}
	@Override
	public double DeiptEquityRatio() {
		double Deipt= (getFinalTotalAccPayabelForFinStat() / (getFinalTotalOwnerForFinStat() + ((getFinalTotalRevenueForFinStat() - (getFinalTotalPurshasesForFinStat() - (0.5 * getFinalTotalPurshasesForFinStat()))) - getFinalTotalPayment())));
		if (Deipt > 0 )
			return Deipt;
		else
			return 0.0;
	}
	@Override
	public double LiquidityRatio() {
		double liquid = (((getFinalTotalCashForFinStat() + getFinalTotalAccRecevForFinStat()+( 0.5 *getFinalTotalPurshasesForFinStat()) +  getFinalTotalVatForFinStat()))/getFinalTotalAccPayabelForFinStat());
		if (liquid > 0 )
			return liquid;
		else
			return 0.0;

	}
	@Override
	public double GrossProfit() {
		double Gross =  (getFinalTotalRevenueForFinStat() - (getFinalTotalPurshasesForFinStat() - (0.5 * getFinalTotalPurshasesForFinStat()))) / getFinalTotalRevenueForFinStat();
		if (Gross > 0 )
			return Gross;
		else
			return 0.0;
	}
	@Override
	public double ActivityRatio() {
		double  Activity =  (getFinalTotalPurshasesForFinStat() - (0.5 * getFinalTotalPurshasesForFinStat())) /(( 0.5 *getFinalTotalPurshasesForFinStat()) / 2) ;
		if (Activity > 0 )
			return Activity;
		else
			return 0.0;
	}
 
}

