package com.example.mikey.maps;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.FileProvider;
import android.view.View;

import com.example.mikey.maps.Trails.DatabaseOperations;
import com.example.mikey.maps.Trails.Trail;
import com.example.mikey.maps.Trails.TrailActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener{

    private GoogleMap mMap;
    static final int CAM_REQUEST = 1;
    Bundle extras;
    float zoomLevel;
    LatLng zoomLocation;
    ArrayList<Marker> markers;
    List<Trail> trailList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        extras = intent.getExtras();

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
        markers = new ArrayList<Marker>();
        if (extras != null) {
            Trail selectedTrail = extras.getParcelable("com.package.Trail");
            zoomLevel = (float) 15.0;
            zoomLocation = new LatLng(selectedTrail.getLatitude(),selectedTrail.getLongtitude());

        }else {
            zoomLevel = (float) 10.0;
             zoomLocation = new LatLng(43.4553, -76.5105);
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(zoomLocation, zoomLevel));



        DatabaseOperations data = new DatabaseOperations(this);
        data.populateDatabase(this);
        trailList = data.getAllTrails();
        LatLng a = new LatLng(trailList.get(0).getLatitude(), trailList.get(0).getLongtitude());
        //System.out.println("Latitude" + trailList.get(1).getLatitude());
        //System.out.println("Longitude" + trailList.get(1).getLongtitude());
       // mMap.addMarker(new MarkerOptions().position(a).title(trailList.get(0).name));
        for(Trail x: trailList){
            //System.out.println("adding " + x.getName() + " trail");
            String activity = Arrays.toString(x.getType());
            activity = activity.replace("[","");
            activity = activity.replace("]","");
            String[] actities = activity.split(",");
            mMap.setOnInfoWindowClickListener(this);
            if(actities[0].equals("hiking")) {
                Marker temp = mMap.addMarker(new MarkerOptions().position(new LatLng(x.getLatitude(),
                        x.getLongtitude()))
                        .title(x.getName() + ": " + activity)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.man_in_hike))
                        .flat(true));

                markers.add(temp);
            }else if(actities[0].equals("biking")){
                Marker temp = mMap.addMarker(new MarkerOptions().position(new LatLng(x.getLatitude(),
                        x.getLongtitude()))
                        .title(x.getName() + ": " + activity)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.bicycle_rider))
                        .flat(true));

                markers.add(temp);
            }else{
                Marker temp = mMap.addMarker(new MarkerOptions().position(new LatLng(x.getLatitude(),
                        x.getLongtitude()))
                        .title(x.getName() + ": " + activity)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pedestrian_walking))
                        .flat(true));
                markers.add(temp);
            }

        }
        //System.out.println("trail list length " + trailList.size());
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


    @Override
    public void onInfoWindowClick(final Marker marker) {

        if (markers.contains(marker)) {
            int index = markers.indexOf(marker);
            Trail activeTrail = trailList.get(index);
            Intent intent = new Intent(MapsActivity.this,TrailActivity.class);
            intent.putExtra("com.package.Trail",activeTrail);
            startActivity(intent);
            //handle click here

        }
    }

    public void listOptions(View view){
        Intent intent = new Intent(this, optionsList.class);
        startActivity(intent);

    }

    public void go (View view){
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photoURI = FileProvider.getUriForFile(MapsActivity.this, BuildConfig.APPLICATION_ID + ".provider", getFile());
        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        startActivityForResult(camera_intent, CAM_REQUEST );
    }

    private File getFile(){
        File folder = new File("sdcard/camera_app");

        if(!folder.exists()){
            folder.mkdir();
        }

        File image_file = new File(folder, "cam_image.jpg");

        return image_file;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        String path = "sdcard/camera_app/cam_image.jpg";
//        imageView.
//    }
}
