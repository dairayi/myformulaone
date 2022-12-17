package com.example.myformulaone;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myformulaone.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent intent = getIntent();

        float x;
        float y;

        String c_lng = intent.getStringExtra("circuit_lng");
        String c_lat = intent.getStringExtra("circuit_lat");
        String c_name = intent.getStringExtra("circuit_name");

        x = Float.parseFloat(c_lat);
        y = Float.parseFloat(c_lng);

        LatLng circuit_name = new LatLng(x,y);
        mMap.addMarker(new MarkerOptions().position(circuit_name).title(c_name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(circuit_name));
    }
}