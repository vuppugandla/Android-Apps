package com.android.vignan.mortgagecalculator.app.util;

import com.android.vignan.mortgagecalculator.app.model.MortgageDetails;

/**
 * Created by Vignan on 7/11/2015.
 */
public class MortgageCalculatorUtil {
    private MortgageDetails details;
    String payOffDate;
    double totalMonthlyPayment;
    double totalPaymentForMortageTerm;

    public MortgageCalculatorUtil(MortgageDetails details){
        this.details=details;
        calculateEstimations();
    }

    public String getPayOffDate() {
        return payOffDate;
    }

    public void setPayOffDate(String payOffDate) {
        this.payOffDate = payOffDate;
    }

    public double getTotalMonthlyPayment() {
        return totalMonthlyPayment;
    }

    public void setTotalMonthlyPayment(double totalMonthlyPayment) {
        this.totalMonthlyPayment = totalMonthlyPayment;
    }

    public double getTotalPaymentForMortageTerm() {
        return totalPaymentForMortageTerm;
    }

    public void setTotalPaymentForMortageTerm(double totalPaymentForMortageTerm) {
        this.totalPaymentForMortageTerm = totalPaymentForMortageTerm;
    }

    public void calculateEstimations(){

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        double purchasePrice = details.getPurchasePrice();
        double interestRate = details.getInterestRatePercent()/100.0;

        double monthlyRate = interestRate/12.0;

        int termInMonths = details.getMortgageTerm()* 12;

        double monthlyPayment =
                (purchasePrice*monthlyRate) /
                        (1-Math.pow(1+monthlyRate, -termInMonths));

        //calculating total monthly payment
        totalMonthlyPayment = monthlyPayment + details.getPropertyTax()/12 + details.getPropertyInsurance()/12;

        //calculating total payment for whole mortage term
        totalPaymentForMortageTerm = monthlyPayment*12*details.getMortgageTerm();

        //calculating payoff date
        int dateYear = Integer.valueOf(details.getFirstPaymentYear());
        dateYear = dateYear + details.getMortgageTerm();
        String dateMonth = details.getFirstPaymentMonth();

        int index = 0;
        for (int i=0; i<months.length; i++) {
            if(months[i].equals(dateMonth)){
                index = i;
                break;
            }
        }

        if(index==0){
            dateMonth = months[11];
            dateYear = dateYear-1;
        }else{
            dateMonth = months[index-1];
        }
        payOffDate = dateMonth+" , "+ dateYear;
    }
}
