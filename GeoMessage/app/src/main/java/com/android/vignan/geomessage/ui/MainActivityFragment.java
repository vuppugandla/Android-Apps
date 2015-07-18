/* Main Activity fragaments lies on top of Main Activity.
It has a floating action which on clicked displays the geo location and address and then sends an SMS to the designated user.
*Author: Vignan Uppugandla
 */
package com.android.vignan.geomessage.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vignan.geomessage.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LocationListener{

    private FloatingActionButton fab;
    private LocationManager mLocManager;
    private String provider;
    private Location location;
    boolean locationServiceEnabled;

    private TextView latitudeText;
    private TextView longitutdeText;
    private TextView latitude;
    private TextView longitude;
    private TextView addressText;
    private TextView address;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        latitudeText = (TextView) rootView.findViewById(R.id.latitudeText);
        longitutdeText = (TextView) rootView.findViewById(R.id.longitudeText);
        latitude = (TextView) rootView.findViewById(R.id.latitude);
        longitude = (TextView) rootView.findViewById(R.id.longitude);
        addressText = (TextView) rootView.findViewById(R.id.addressText);
        address = (TextView) rootView.findViewById(R.id.address);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mLocManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationServiceEnabled = mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!locationServiceEnabled){
            disabledSettingsAlert();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if enabled and if not send user to the GSP settings
                locationServiceEnabled = mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                if (locationServiceEnabled) {
                    showLocation();
                } else {
                    Toast.makeText(getActivity(),"Location Service Not Enabled",Toast.LENGTH_SHORT).show();
                }

            }
        });
    return rootView;
    }

    private void sendMessage(String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(String.valueOf(R.string.mobileNum), null, message, null, null);
            Toast.makeText(getActivity(),"SMS Sent\n"+message,Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getActivity(),"Unable to SMS",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            Log.e("SMS Send Exception: ",e.getMessage());
        }
    }

    public void showLocation(){
        mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, this);
        Criteria criteria = new Criteria();
        provider = mLocManager.getBestProvider(criteria,false);
        location = mLocManager.getLastKnownLocation(provider);
        // Initialize the location fields
        if (location != null) {
            onLocationChanged(location);
            sendMessage("latitude: "+location.getLatitude()+"\nlongitude: "+location.getLongitude());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
            double lat = (double) (location.getLatitude());
            double lng = (double) (location.getLongitude());
        latitudeText.setVisibility(TextView.VISIBLE);
        longitutdeText.setVisibility(TextView.VISIBLE);
        latitude.setText(String.valueOf(lat));
        longitude.setText(String.valueOf(lng));
        getAddress(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getActivity(), "Location Provider Enabled: " + provider,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    private void getAddress(Location location) {
        Geocoder gcd = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(location.getLatitude(),
                    location.getLongitude(), 1);
            String street = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            addressText.setVisibility(View.VISIBLE);
            address.setText(street + "\n" + city + "\n" + state + "\n" + country + "\n" + postalCode);
        } catch (IOException e) {
            Log.e("Exception geoCode: \n",e.getMessage());
        }
    }

    public void disabledSettingsAlert(){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Enable Location Services");
        // go to the settings
        alert.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });
        // Cancel
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.show();
    }
}
