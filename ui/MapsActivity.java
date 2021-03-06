package com.example.jpm.offthestreets.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jpm.offthestreets.R;
import com.example.jpm.offthestreets.model.HomelessShelter;
import com.example.jpm.offthestreets.model.ShelterCollection;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Boolean mLocationPermissionsGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private Spinner spGender;
    private Spinner spAgeRange;
    private EditText etShelterName;

    ShelterCollection shelter = ShelterCollection.INSTANCE;

    public HomelessShelterListActivity list = new HomelessShelterListActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getLocationPermission();

        spGender = findViewById(R.id.spinnerGender);
        String[] genders = {"", "Men", "Women", "Both"};
        ArrayAdapter<String> gendersAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, genders);
        spGender.setAdapter(gendersAdapter);
        spAgeRange = findViewById(R.id.spinnerAgeRange);
        String[] ageRanges = {"", "Families with newborns", "Children", "Young Adults", "Anyone"};
        ArrayAdapter<String> ageAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, ageRanges);
        spAgeRange.setAdapter(ageAdapter);
        etShelterName = findViewById(R.id.etShelterName);

    }

    private void initMap(){
        Log.d("TAG", "initmap is ready");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapButton);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is ready", Toast.LENGTH_SHORT).show();
        Log.d("TAG", "onMapReady: on map ready");
        mMap = googleMap;

        //Adds pin based on lat and lon
        LatLng Atl = new LatLng(33.777175,-84.396543);
        //Displays data when clicked
        mMap.addMarker(new MarkerOptions().position(Atl).title("Atlanta"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Atl));



    }


    private void getLocationPermission(){
        Log.d("TAG", "getLocationPermissions Ready");
        String [] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
            }
            else{
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("TAG", "OnRequestPermissions called");
        mLocationPermissionsGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i <grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            Log.d("TAG", "onRequestPermission Failed");
                            return;
                        }
                    }
                    Log.d("TAG", "onRequestPermission Granted");
                    mLocationPermissionsGranted = true;
                    // initialize map
                    initMap();

                }
            }
        }
    }

//    private void getDeviceLocation(){
//        Log.d("TAG", "get device location: getting current device location");
//
//    }


}
