package tourguide.lightidea.com.tourguide.activity.MainAndOther;

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
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
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
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;
import tourguide.lightidea.com.tourguide.R;
import tourguide.lightidea.com.tourguide.activity.Restaurant.RestaurantSingleActivity;
import tourguide.lightidea.com.tourguide.adapter.DialogAdapter.DialogAdapter;
import tourguide.lightidea.com.tourguide.adapter.recyclerAdapter.MainRecyclerAdapter;
import tourguide.lightidea.com.tourguide.model.MainList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG ="Main" ;
    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private List<MainList> mList = new ArrayList<>();
    private String city = null;

    private TextView mCity;
    private CardView mCardView;
    private static final String fine_location = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String cource_location = Manifest.permission.ACCESS_COARSE_LOCATION;
    private NavigationView mNavView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    protected static final int Request = 001;
    String button="0";

    //segment language change button

    private SegmentedGroup group ;
    private RadioButton button_myanmar,button_english,button_chinese;

    private TextView dialog_single_textview;

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

        group = findViewById(R.id.button_king);
        button_myanmar = findViewById(R.id.button_myanmar);
        button_english = findViewById(R.id.button_english);
        button_chinese = findViewById(R.id.button_chinese);
         button = readLanguage();

        if(button.equals("0")){
            button_myanmar.setChecked(true);
        }
        else if(button.equals("1")){
            button_english.setChecked(true);
        }
        else if(button.equals("2")){
            button_chinese.setChecked(true);
        }


        CollapsingToolbarLayout c = findViewById(R.id.main_activity_collapsing_toolbar);
        c.setTitle("");
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Flash Tour");
        mNavView = findViewById(R.id.main_navigationview);
        mDrawerLayout =  findViewById(R.id.main_drawerlayout);
        mRecyclerView=findViewById(R.id.recyclerview);
        mCity = findViewById(R.id.main_city);
        mCardView=findViewById(R.id.main_cardview);

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showingDialog();
            }
        });



        mToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mToggle.syncState();
        mDrawerLayout.addDrawerListener(mToggle);
        mNavView.setNavigationItemSelectedListener(this);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.button_myanmar:
                        saveLanguage("0");
                        recreate();
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.button_english:
                        saveLanguage("1");
                        recreate();
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.button_chinese:
                        saveLanguage("2");
                        recreate();
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
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


        dialog_single_textview=view.findViewById(R.id.dialog_single_textvewi);

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
        List<String> list_bur=new ArrayList<>();
        list_bur.add("မႏၲေလး");
        list_bur.add("ရန်ကုန်");
        list_bur.add("ပြင်ဦးလွင်");
        list_bur.add("တောင်ကြီး");
        list_bur.add("လားရှိုး");
        list_bur.add("နေပြည်တော်");
        list_bur.add("စစ်ကိုင်း");
        list_bur.add("ျမစ္ၾကီးနား");
        list_bur.add("မူဆယ္");
        List<String> list_chi=new ArrayList<>();
        list_chi.add("曼德勒");
        list_chi.add("仰光");
        list_chi.add("Mandalay_Bur");
        list_chi.add("东枝");
        list_chi.add("腊戍");
        list_chi.add("内比都");
        list_chi.add("实皆");
        list_chi.add("Mandalay_Bur");
        list_chi.add("Mu Sae");
        List mList = new ArrayList();
        mList.add(RestaurantSingleActivity.class);
        mList.add(MainActivity.class);
        if(button.equals("0")){
            dialog_single_textview.setText("Choose City M");
            dialog_recyclerview.setAdapter(new DialogAdapter(list_bur,MainActivity.this,1,mList,"haha","haha","haha"));
        }
        else if(button.equals("1")){
            dialog_single_textview.setText("Choose City ");

            dialog_recyclerview.setAdapter(new DialogAdapter(list,MainActivity.this,1,mList,"haha","haha","haha"));

        }
        else{
            dialog_single_textview.setText("Choose City C");

            dialog_recyclerview.setAdapter(new DialogAdapter(list_chi,MainActivity.this,1,mList,"haha","haha","haha"));

        }
        mDialog.show();

    }



    private void settingRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setAdapter(new MainRecyclerAdapter(MainActivity.this,mList,button));
    }

    private void settingList() {

        String name1,name2,name3,name4,name5,name6;

        if(button.equals("1")){
            name1="Places to Travel";
            name2="Festivals";
            name3="Hotel";
            name4="Restaurant";
            name5="Taxi";
            name6="Currency";
        }
        else if(button.equals("0")){
            name1="လည္စရာေနရာမ်ား";
            name2="ပြဲေတာ္မ်ား";
            name3="ဟိုတယ္မ်ား";
            name4="စားေသာက္ဆိုင္မ်ား";
            name5="ကားငွားရန္";
            name6="ေငြလဲလွယ္ႏွုန္း";
        }
        else{
            name1="旅行的地方";
            name2="节";
            name3="酒店";
            name4="餐厅";
            name5="出租车";
            name6="货币";
        }
        mList.add(new MainList(name1,R.drawable.place));
        mList.add(new MainList(name2,R.drawable.festival));
        mList.add(new MainList(name3,R.drawable.hotel));
        mList.add(new MainList(name4,R.drawable.eat));
        mList.add(new MainList(name6,R.drawable.currency));



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

    private String readLanguage() {
        SharedPreferences sharedPref = getSharedPreferences("language", Context.MODE_PRIVATE);
        String default_data = "0";
        String value = sharedPref.getString("data", default_data);
        return value;

    }

    private void saveLanguage(String value) {
        SharedPreferences sharedPref = getSharedPreferences("language", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("data", value);
        editor.commit();
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
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            }
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.about){
            Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.exit){
            new  MaterialDialog.Builder(this)
                    .title("Exit")
                    .content("Are u sure want to exit")
                    .positiveText("Yes")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                finishAffinity();
                            }
                        }
                    })
                    .negativeText("Cancel")
                    .show();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
