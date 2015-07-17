/* This class is the main activity which is considered to be home screen of app. User interacts with this app.
This app the generates intent and connects to Results Activity
Author: Vignan U
 */
package com.android.vignan.mortgagecalculator.app;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.vignan.mortgagecalculator.app.database.MortgageAppContract.MortgageEntry;
import com.android.vignan.mortgagecalculator.app.model.MortgageDetails;
import com.android.vignan.mortgagecalculator.app.util.MortgageCalculatorUtil;


public class MainActivity extends Activity{
    private static final long serialVersionUID = -2517851941873251699L;
    private Button mainButton;
    private EditText purchasePriceView;
    private EditText downPaymentView;
    private EditText mortgageTermView;
    private EditText interestRateView;
    private EditText propertyTaxView;
    private EditText propertyInsuranceView;
    private EditText pmiView;
    private EditText zipCodeView;
    private Spinner firstPaymentYearView;
    private Spinner firstPaymentMonthView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        purchasePriceView = (EditText) findViewById(R.id.purchasePrice);
        downPaymentView = (EditText) findViewById(R.id.downPayment);
        mortgageTermView = (EditText) findViewById(R.id.mortgageTerm);
        interestRateView = (EditText) findViewById(R.id.interestRate);
        propertyTaxView = (EditText) findViewById(R.id.propertyTax);
        propertyInsuranceView = (EditText) findViewById(R.id.propertyInsurance);
        pmiView = (EditText) findViewById(R.id.pmi);
        zipCodeView = (EditText) findViewById(R.id.zipCode);
        firstPaymentYearView= (Spinner) findViewById(R.id.yearSpinner);
        firstPaymentMonthView=(Spinner) findViewById(R.id.monthSpinner);

        mainButton = (Button) findViewById(R.id.button_calculator);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MortgageDetails entries = new MortgageDetails();
                entries.setPurchasePrice(Double.parseDouble(purchasePriceView.getText().toString()));
                entries.setDownPaymentPercent(Double.parseDouble(downPaymentView.getText().toString()));
                entries.setMortgageTerm(Integer.parseInt(mortgageTermView.getText().toString()));
                entries.setInterestRatePercent(Double.parseDouble(interestRateView.getText().toString()));
                entries.setPropertyTax(Double.parseDouble(propertyTaxView.getText().toString()));
                entries.setPropertyInsurance(Double.parseDouble(propertyInsuranceView.getText().toString()));
                entries.setPmi(Double.parseDouble(pmiView.getText().toString()));
                entries.setZipCode(Integer.parseInt(zipCodeView.getText().toString()));
                entries.setFirstPaymentYear(Integer.parseInt(firstPaymentYearView.getSelectedItem().toString()));
                entries.setFirstPaymentMonth(firstPaymentMonthView.toString());

                MortgageCalculatorUtil calculations = new MortgageCalculatorUtil(entries);
                entries.setTotalMonthlyPayment(calculations.getTotalMonthlyPayment());
                entries.setTotalOfPaymentForMortgageTerm(calculations.getTotalPaymentForMortageTerm());
                entries.setPayOffDate(calculations.getPayOffDate());

                addToDatabase(entries);

                Intent showEstimationsIntent = new Intent(MainActivity.this, ResultsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("Details", entries);
                showEstimationsIntent.putExtra("zipcode", entries.getZipCode());
                startActivity(showEstimationsIntent);
            }
        });
    }

    private void addToDatabase(MortgageDetails entries) {
        ContentValues mortgageValues = new ContentValues();

        mortgageValues.put(MortgageEntry.COLUMN_PURCHASE_PRICE, entries.getPurchasePrice());
        mortgageValues.put(MortgageEntry.COLUMN_DOWN_PAYMENT_PERCENT, entries.getDownPaymentPrecent());
        mortgageValues.put(MortgageEntry.COLUMN_MORTGAGE_TERM, entries.getMortgageTerm());
        mortgageValues.put(MortgageEntry.COLUMN_INTEREST_RATE, entries.getInterestRatePercent());
        mortgageValues.put(MortgageEntry.COLUMN_PROPERTY_TAX, entries.getPropertyTax());
        mortgageValues.put(MortgageEntry.COLUMN_PROPERTY_INSURANCE, entries.getPropertyInsurance());
        mortgageValues.put(MortgageEntry.COLUMN_PMI, entries.getPmi());
        mortgageValues.put(MortgageEntry.COLUMN_ZIP_CODE, entries.getZipCode());
        mortgageValues.put(MortgageEntry.COLUMN_FIRST_PAYMENT_MONTH, entries.getFirstPaymentMonth());
        mortgageValues.put(MortgageEntry.COLUMN_FIRST_PAYMENT_YEAR, entries.getFirstPaymentYear());
        mortgageValues.put(MortgageEntry.COLUMN_TOTAL_MONTHLY_PAYMENT, entries.getTotalMonthlyPayment());
        mortgageValues.put(MortgageEntry.COLUMN_TOTAL_PAYMENT_MORTGAGE_TERM, entries.getTotalOfPaymentForMortgageTerm());
        mortgageValues.put(MortgageEntry.COLUMN_PAYOFF_DATE, entries.getPayOffDate());

        Uri insertedUri = this.getContentResolver().insert(
                MortgageEntry.CONTENT_URI,
                mortgageValues);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
