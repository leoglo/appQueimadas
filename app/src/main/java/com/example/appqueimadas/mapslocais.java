package com.example.appqueimadas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class mapslocais extends AppCompatActivity {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapslocais);

        supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.google_map);

        client = LocationServices.getFusedLocationProviderClient(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            getCorrenteLocalizacao();

        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
    }
    private void getCorrenteLocalizacao(){

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
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if(location != null){
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            final LatLng latLng1 =new LatLng(location.getLatitude(), location.getLongitude());
                            map = googleMap;
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 10));




                            final List<LocalMemoria> listalocais = new ArrayList<LocalMemoria>();
                            Manute manute = new Manute();
                            String url = manute.Murl+"listaLocal.php";

                            Ion.with(mapslocais.this)
                                    .load(url)
                                    .asJsonArray()
                                    .setCallback(new FutureCallback<JsonArray>() {
                                        @Override
                                        public void onCompleted(Exception e, JsonArray result) {
                                            double latitude = 0;
                                            double longitude = 0;
                                            for(int i = 0; i < result.size();i ++){
                                                JsonObject object = result.get(i).getAsJsonObject();

                                                LocalMemoria Local = new LocalMemoria();
                                                Local.setId(object.get("id").getAsInt());
                                                Local.setLatitude(object.get("latitude").getAsString());
                                                Local.setLongitude(object.get("longitude").getAsString());
                                                listalocais.add(Local);
                                          }

                                            for(int i = 0; i < listalocais.size();i ++){

                                               latitude = Double.parseDouble(listalocais.get(i).getLatitude());
                                               longitude = Double.parseDouble(listalocais.get(i).getLongitude());
                                               LatLng latLng =new LatLng(latitude, longitude);
                                               MarkerOptions options = new MarkerOptions();
                                               options.position(latLng);
                                               map.addMarker(options);
                                            }
                                        }
                                    });


                        }
                    });
                }
            }
        });

    }
    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 44){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCorrenteLocalizacao();
            }
        }
    }



}