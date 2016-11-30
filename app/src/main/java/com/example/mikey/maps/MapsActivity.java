package com.example.mikey.maps;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void changeType(View view){
        if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL){
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = (float) 10.0;
        LatLng oswego = new LatLng(43.4553, -76.5105);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(oswego, zoomLevel));

        TrailsList trails = new TrailsList(this);
        DatabaseOperations data = new DatabaseOperations(this);
        List<Trail> trailList = trails.getTrailList();
        LatLng a = new LatLng(trailList.get(0).getLatitude(), trailList.get(0).getLongtitude());
        System.out.println("Latitude" + trailList.get(1).getLatitude());
        System.out.println("Longitude" + trailList.get(1).getLongtitude());
       // mMap.addMarker(new MarkerOptions().position(a).title(trailList.get(0).name));
        for(Trail x: trailList){
            System.out.println("adding " + x.getName() + " trail");
            mMap.addMarker(new MarkerOptions().position(new LatLng(x.getLatitude(), x.getLongtitude())).title(x.getName()));
        }
        System.out.println("trail list length " + trailList.size());

        // Add a marker in Sydney and move the camera
        //LatLng oswego = new LatLng(43.4553, -76.5105);
        //mMap.addMarker(new MarkerOptions().position(oswego).title("Oswego, NY"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(oswego));
    }

    public void onSearch(View view)
    {
        float zoomLevel = (float) 16.0;
        EditText location_tf = (EditText)findViewById(R.id.TFaddress);
        String location = location_tf.getText().toString();
        List<Address> addressList = null;

        if(!location.isEmpty() || !location.equals("")){
            Geocoder geocoder = new Geocoder(this);
            try {
               addressList = geocoder.getFromLocationName(location , 1);

            } catch (IOException e) {
                e.printStackTrace();
            }

            android.location.Address address = addressList.get(0);
            LatLng latlng = new LatLng(address.getLatitude(),address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latlng).title(location));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoomLevel));
        } else {

        }

    }

    public void listOptions(View view){
        Intent intent = new Intent(this, optionsList.class);
        startActivity(intent);

    }

    public void setCamera(){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//            }
    }
}
