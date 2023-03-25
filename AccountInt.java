package application;

import java.util.Date;

public interface AccountInt {
	double allFinalTotalallFinalTotalRevenue();
	double allFinalTotalallFinalTotalPurchases();
	void addRevenueRead(Date date,String name,String voucher,String item, long quantity, double value,String type,double prePaiedTax,double total,
			double finalTotal,double vat);
	void addPuechsesRead(Date date,String name,String voucher,String item, long quantity, double value,String type,double prePaiedTax,double total,
			double finalTotal,double vat);
	double setTotal();
	double setFinalTotal();
	void ManageRevenue();
	void ManagePurcahse();
	double getFinalTotalPayment();
	double getFinalTotalRevenueForFinStat();
	double getFinalTotalPurshasesForFinStat();
	double getFinalTotalOwnerForFinStat();
	double getFinalTotalCashForFinStat();
	double getFinalTotalAccPayabelForFinStat();
	double getFinalTotalAccRecevForFinStat();
	double getFinalTotalVatForFinStat();
	double DeiptEquityRatio();
	double LiquidityRatio();
	double GrossProfit();
	double ActivityRatio();
}

