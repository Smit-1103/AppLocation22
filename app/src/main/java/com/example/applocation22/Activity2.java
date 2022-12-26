package com.example.applocation22;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.List;
import java.util.Locale;

public class Activity2 extends Activity implements LocationListener {
    TextView tvArea, tvCity, tvState, tvCountry, tvPin;
    Location loc;
    LocationManager man;
    double lati = 0, longi = 0;
    Address address;
    boolean isGps, isNet;
    Geocoder geo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        tvArea = findViewById(R.id.tvArea);
        tvCity = findViewById(R.id.tvCity);
        tvState = findViewById(R.id.tvState);
        tvCountry = findViewById(R.id.tvCountry);
        tvPin = findViewById(R.id.tvPin);

        man = (LocationManager) getSystemService(LOCATION_SERVICE); // mobile pase thi location ni service lidhi
        isNet = man.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        isGps = man.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (isNet || isGps) {
            if (isNet) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                man.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 300, this);

                if(man!=null){
                    loc = man.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if(loc!=null)
                    {
                        lati = loc.getLatitude();
                        longi = loc.getLongitude();
                    }
                }
            }

            if(isGps)
            {
                man.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,300,this);

                if (man!=null)
                {
                    loc = man.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    if(loc!=null)
                    {
                        lati = loc.getLatitude();
                        longi = loc.getLongitude();
                    }
                }
            }

            Toast.makeText(getApplicationContext(),"LATI : "+lati+" , LONGI : "+longi, Toast.LENGTH_LONG).show();

            try
            {
                geo = new Geocoder( this, Locale.getDefault());

                List<Address> list = geo.getFromLocation(lati,longi,1);

                address = list.get(0);

                String area = address.getAddressLine(0);//full address
                tvArea.setText(area);

                String city = address.getLocality();
                tvCity.setText(city);

                String state = address.getAdminArea();
                tvState.setText(state);

                String country = address.getCountryName();
                tvCountry.setText(country);

                String pin = address.getPostalCode();
                tvPin.setText(pin);
            }
            catch (Exception e) {}
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        //jyare jyare badlase location am am avu thase

        lati = location.getLatitude();
        longi = location.getLongitude();

        try
        {
            geo = new Geocoder( this, Locale.getDefault());

            List<Address> list = geo.getFromLocation(lati,longi,1);

            address = list.get(0);

            String area = address.getAddressLine(0);//full address
            tvArea.setText(area);

            String city = address.getLocality();
            tvCity.setText(city);

            String state = address.getAdminArea();
            tvState.setText(state);

            String country = address.getCountryName();
            tvCountry.setText(country);

            String pin = address.getPostalCode();
            tvPin.setText(pin);
        }

        catch (Exception e) {

        }
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}










//import android.Manifest;
//import android.app.Activity;
//import android.content.pm.PackageManager;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;

//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;

//import java.util.List;
//import java.util.Locale;

//public class Activity2 extends Activity implements LocationListener {
//    TextView tvArea, tvCity, tvState, tvCountry, tvPin;
//    Location loc;
//    Address address;
//    LocationManager man;
//    double lati = 0, longi = 0;
//    boolean isGps, isNet;
//    Geocoder geo;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_2);

//        tvArea = findViewById(R.id.tvArea);
//        tvCity = findViewById(R.id.tvCity);
//        tvState = findViewById(R.id.tvState);
//        tvCountry = findViewById(R.id.tvCountry);
//        tvPin = findViewById(R.id.tvPin);

//        man = (LocationManager) getSystemService(LOCATION_SERVICE); // mobile pase thi location ni service lidhi
//        isNet = man.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        isGps = man.isProviderEnabled(LocationManager.GPS_PROVIDER);

//        if (isNet || isGps) {
//            if (isNet) {
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                man.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 3000, this);

//                if(man!=null){
//                    loc = man.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

//                    if(loc!=null){
//                        lati = loc.getLatitude();
//                        longi = loc.getLatitude();
//                    }
//                }
//            }

//            if (isGps){
//                man.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,3000,this);

//                if(man!=null){
//                    loc = man.getLastKnownLocation(LocationManager.GPS_PROVIDER);

//                    if(loc!=null){
//                        lati = loc.getLatitude();
//                        longi = loc.getLatitude();
//                    }
//                }
//            }

//            Toast.makeText(getApplicationContext(),"LATI : "+lati+" , LONGI : "+longi,Toast.LENGTH_LONG).show();

//        }
//        try {

//            geo = new Geocoder(this, Locale.getDefault());  // geo = new Geocoder(this, Locale.canada); jr language joye a ahiya thi made

//            List<Address> list = geo.getFromLocation(lati,longi,1); // collection of all addresses

//            address = list.get(0); // 1 j address mangayu nd s array index pramane 0 par avse atle

//            String area = address.getAddressLine(0); //area name
//            tvArea.setText(area);

//            String city = address.getLocality();  // city name
//            tvCity.setText(city);

//            String state = address.getAdminArea(); //state name
//            tvCity.setText(state);

//            String country = address.getCountryName(); //country name
//            tvCity.setText(country);

//            String pin = address.getPostalCode();  //pin code
//            tvCity.setText(pin);

//        }
//        catch (Exception e){}

//    }

//    @Override
//    public void onLocationChanged(@NonNull Location location) {
//
//    }

//    @Override
//    public void onLocationChanged(@NonNull List<Location> locations) {
//        LocationListener.super.onLocationChanged(locations);
//    }

//    @Override
//    public void onFlushComplete(int requestCode) {
//        LocationListener.super.onFlushComplete(requestCode);
//    }

//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//        LocationListener.super.onStatusChanged(provider, status, extras);
//    }

//    @Override
//    public void onProviderEnabled(@NonNull String provider) {
//        LocationListener.super.onProviderEnabled(provider);
//    }

//    @Override
//    public void onProviderDisabled(@NonNull String provider) {
//        LocationListener.super.onProviderDisabled(provider);
//    }

//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }
//}