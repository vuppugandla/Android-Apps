/* This class is the model class for storing all the details in an object for transfer and use.
Author: Vignan U
 */
package com.android.vignan.mortgagecalculator.app.model;

import java.io.Serializable;

/**
 * Created by Vignan on 7/11/2015.
 */
public class MortgageDetails implements Serializable {
    private double purchasePrice;
    private double downPaymentPrecent;
    private int mortgageTerm;
    private double interestRatePercent;
    private double propertyTax;
    private double propertyInsurance;
    private double pmi;
    private int zipCode;
    private int firstPaymentYear;
    private String firstPaymentMonth;

    private double totalMonthlyPayment;
    private double totalOfPaymentForMortgageTerm;
    private String payOffDate;


    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getDownPaymentPrecent() {
        return downPaymentPrecent;
    }

    public void setDownPaymentPercent(double downPaymentPrecent) {
        this.downPaymentPrecent = downPaymentPrecent;
    }

    public int getMortgageTerm() {
        return mortgageTerm;
    }

    public void setMortgageTerm(int mortgageTerm) {
        this.mortgageTerm = mortgageTerm;
    }

    public double getInterestRatePercent() {
        return interestRatePercent;
    }

    public void setInterestRatePercent(double interestRatePercent) {
        this.interestRatePercent = interestRatePercent;
    }

    public double getPropertyTax() {
        return propertyTax;
    }

    public void setPropertyTax(double propertyTax) {
        this.propertyTax = propertyTax;
    }

    public double getPropertyInsurance() {
        return propertyInsurance;
    }

    public void setPropertyInsurance(double propertyInsurance) {
        this.propertyInsurance = propertyInsurance;
    }

    public double getPmi() {
        return pmi;
    }

    public void setPmi(double pmi) {
        this.pmi = pmi;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getFirstPaymentYear() {
        return firstPaymentYear;
    }

    public void setFirstPaymentYear(int firstPaymentYear) {
        this.firstPaymentYear = firstPaymentYear;
    }

    public String getFirstPaymentMonth() {
        return firstPaymentMonth;
    }

    public void setFirstPaymentMonth(String firstPaymentMonth) {
        this.firstPaymentMonth = firstPaymentMonth;
    }

    public double getTotalMonthlyPayment() {
        return totalMonthlyPayment;
    }

    public void setTotalMonthlyPayment(double totalMonthlyPayment) {
        this.totalMonthlyPayment = totalMonthlyPayment;
    }

    public double getTotalOfPaymentForMortgageTerm() {
        return totalOfPaymentForMortgageTerm;
    }

    public void setTotalOfPaymentForMortgageTerm(double totalOfPaymentForMortgageTerm) {
        this.totalOfPaymentForMortgageTerm = totalOfPaymentForMortgageTerm;
    }

    public String getPayOffDate() {
        return payOffDate;
    }

    public void setPayOffDate(String payOffDate) {
        this.payOffDate = payOffDate;
    }
}
