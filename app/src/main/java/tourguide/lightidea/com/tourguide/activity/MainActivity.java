package tourguide.lightidea.com.tourguide.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.adapter.DialogAdapter.DialogAdapter;
import tourguide.lightidea.com.tourguide.adapter.recyclerAdapter.MainRecyclerAdapter;
import tourguide.lightidea.com.tourguide.model.MainList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG ="Main" ;
    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private List<MainList> mList = new ArrayList<>();
    private String city = null;

    private TextView mCity;
    private CardView mCardView;
    private static final String fine_location = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String cource_location = Manifest.permission.ACCESS_COARSE_LOCATION;

    protected static final int Request = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);
        Log.i(TAG,"agian working");
        if(isGoogleServicesOk()){

            init();
        }




    }

    private void getLocationPermission(){
        String permission[] ={Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
        if(ContextCompat.checkSelfPermission(this,fine_location)== PackageManager.PERMISSION_GRANTED){

            if(ContextCompat.checkSelfPermission(this,cource_location)== PackageManager.PERMISSION_GRANTED){

            }
            else{
                ActivityCompat.requestPermissions(this,permission,1);
            }

        }
        else{
            ActivityCompat.requestPermissions(this,permission,1);
        }
    }

    private void init() {
        givingId();

        doingStatusWork();

        doingPrefence();

        doingIntent();

        settingToolbar();

        initCollapsingToolbar();

        settingList();

        settingRecyclerView();
    }

    private void displayLocationSettingsRequest(Context context) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(TAG, "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            status.startResolutionForResult(MainActivity.this, Request);

                        } catch (IntentSender.SendIntentException e) {
                            Log.i(TAG, "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
                        }

    private void doingPrefence() {
        String name = readFile();
        mCity.setText(name);
    }

    private void doingIntent() {
        if(getIntent()!=null){
            city=  getIntent().getStringExtra("city");
            if(city!=null){
                saveFile(city);
                mCity.setText(city);
            }
        }
    }

    private void doingStatusWork() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


    //All the id giving Here
    private void givingId() {
        CollapsingToolbarLayout c = findViewById(R.id.main_activity_collapsing_toolbar);
        c.setTitle("");
        toolbar=findViewById(R.id.toolbar);
        mRecyclerView=findViewById(R.id.recyclerview);
        mCity = findViewById(R.id.main_city);
        mCardView=findViewById(R.id.main_cardview);

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showingDialog();
            }
        });

    }

    public boolean isGoogleServicesOk(){
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if(available== ConnectionResult.SUCCESS){
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this,available,0);
            dialog.show();
        }
        else{
            Toast.makeText(this, "we cannot make map reqest", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void showingDialog() {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                        .customView(R.layout.dialog_layout,false);

        MaterialDialog mDialog = builder.build();

        View view= mDialog.getView();




        RecyclerView dialog_recyclerview = view.findViewById(R.id.dialog_recyclerview);
        dialog_recyclerview.setHasFixedSize(true);
        dialog_recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        List<String> list = new ArrayList<>();
        list.add("Mandalay");
        list.add("Yangon");
        list.add("Pyin Oo Lwin");
        list.add("Taung Gyi");
        list.add("Lashio");
        list.add("Nay Pyi Taw");
        list.add("Sagaing");
        list.add("Myint Kyi Nar");
        list.add("Mu Sae");
        dialog_recyclerview.setAdapter(new DialogAdapter(list,this));
        mDialog.show();

    }

    private void settingToolbar() {
        setSupportActionBar(toolbar);
    }

    private void settingRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setAdapter(new MainRecyclerAdapter(MainActivity.this,mList));
    }

    private void settingList() {
        mList.add(new MainList("Place",R.drawable.place));
        mList.add(new MainList("Festival",R.drawable.festival));
        mList.add(new MainList("Hotel",R.drawable.hotel));
        mList.add(new MainList("Restaurant",R.drawable.eat));
        mList.add(new MainList("Taxi",R.drawable.taxi));
        mList.add(new MainList("Currency",R.drawable.currency));



    }

    @Override
    protected void onResume() {
        super.onResume();
        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }

            CollapsingToolbarLayout collapsing_toolbar_layout = findViewById(R.id.main_activity_collapsing_toolbar);
            collapsing_toolbar_layout.setExpandedTitleTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }
    }
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.main_activity_collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.main_activity_appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("Tour Guide");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case Request:
             switch (resultCode){
                 case MainActivity.RESULT_OK:
                     Log.i(TAG,"working");
                     recreate();
                     break;
                 case MainActivity.RESULT_CANCELED:
                     displayLocationSettingsRequest(MainActivity.this);
break;
             }

             break;
        }
    }

    private String readFile() {
        SharedPreferences sharedPref = getSharedPreferences("mySave", Context.MODE_PRIVATE);
        String default_data = "Travel To . . .";
        String value = sharedPref.getString("data", default_data);
        return value;

    }

    private void saveFile(String value) {
        SharedPreferences sharedPref = getSharedPreferences("mySave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("data", value);
        editor.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLocationPermission();
        displayLocationSettingsRequest(MainActivity.this);



    }
}
