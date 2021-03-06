package tourguide.lightidea.com.tourguide.fragment.s.PlaceSingleFragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import tourguide.lightidea.com.tourguide.R;

public class LocationTwo extends Fragment {

    private SupportMapFragment mapFragment;

    private Boolean mLocationPermissionGranted = false;

    private GoogleMap mMap;


    private FusedLocationProviderClient mFusedLocationProviderClient;

    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 13;
    private Location mLastKnownLocation;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private double DoubleLag, DoubleLong;
    private LocationRequest mLocationRequest = LocationRequest.create();

    View view;

    public LocationTwo() {
    }


    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_location_two, container, false);
        getLocationPermission();


        testingForLocation();
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        Bundle bundle = getArguments();
        if (bundle != null) {

            if (TextUtils.isEmpty(bundle.getString("lag"))) {
                DoubleLag = 21.9928775;
                DoubleLong = 96.0943637;
            } else {
                DoubleLag = Double.parseDouble(bundle.getString("lag"));
                DoubleLong = Double.parseDouble(bundle.getString("log"));
            }


        }


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initMap();
    }

    private void testingForLocation() {
        mLocationRequest.setInterval(60000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    private void initMap() {

        mapFragment = SupportMapFragment.newInstance();
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                updateLocationUI();
                getDeviceLocation();
            }
        });

        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                Log.e("arun", "granted");
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
                Log.e("arun", "not granted");
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }

    }

    //&&task.getResult()!=null
    private void getDeviceLocation() {


        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()&&task.getResult()!=null) {
                            mLastKnownLocation = task.getResult();
                            Log.e("arun",mLastKnownLocation.getLatitude()+"arun");
                            mMap.setMyLocationEnabled(true);
                            mMap.getUiSettings().setMyLocationButtonEnabled(true);
                            mMap.getUiSettings().setZoomControlsEnabled(true);
                            ArrayList<LatLng> points = new ArrayList<LatLng>();
                                mMap.addMarker(new MarkerOptions().position( new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude())).title("Start"));
                                mMap.addMarker(new MarkerOptions().position(new LatLng(DoubleLag, DoubleLong)).title("end"));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
                                points.add(new LatLng(mLastKnownLocation.getLatitude(),mLastKnownLocation.getLongitude()));
                                points.add(new LatLng(DoubleLag, DoubleLong));

//                            else{
//                                mMap.addMarker(new MarkerOptions().position( new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude())).title("Start"));
//                                mMap.addMarker(new MarkerOptions().position(new LatLng(DoubleLag, DoubleLong)).title("end"));
//                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
//                                mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
//                                points.add(new LatLng(-33.8523341,151.2106085));
//                                points.add(new LatLng(DoubleLag, DoubleLong));
//                            }

                            PolylineOptions polyLineOptions = new PolylineOptions();
                            polyLineOptions.width(7);
                            polyLineOptions.geodesic(true);
                            polyLineOptions.addAll(points);
                            Polyline polyline = mMap.addPolyline(polyLineOptions);
                            polyline.setGeodesic(true);
                            Log.e("arun","this is working");

                     }
                      else {
                            Log.e("arun", "Current location is null. Using defaults.");
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);

                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
}



