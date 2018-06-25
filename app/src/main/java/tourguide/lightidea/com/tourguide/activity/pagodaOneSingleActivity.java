package tourguide.lightidea.com.tourguide.activity;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import tourguide.lightidea.com.tourguide.R;

public class pagodaOneSingleActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Toolbar mToolbar;

    private String pagodaName,pagodaUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagoda_one_single);
           pagodaName = getIntent().getStringExtra("name");
           pagodaUrl = getIntent().getStringExtra("url");
        Toast.makeText(this, pagodaName, Toast.LENGTH_SHORT).show();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        givingId();

        settingToolbar();

        initCollapsingToolbar();

    }

    private void givingId() {
        mImageView = findViewById(R.id.pagodaone_single_imageview);

        Glide.with(pagodaOneSingleActivity.this).load(pagodaUrl).into(mImageView);
        mToolbar = findViewById(R.id.pagodaone_single_toolbar);
    }

    private void settingToolbar() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }

            CollapsingToolbarLayout collapsing_toolbar_layout = findViewById(R.id.pagodaone_single_collapsing_toolbar);
            collapsing_toolbar_layout.setExpandedTitleTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }
    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.pagodaone_single_collapsing_toolbar);
        collapsingToolbar.setTitle(pagodaName);
        AppBarLayout appBarLayout = findViewById(R.id.pagodaone_single_appbar);
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
                    collapsingToolbar.setTitle(pagodaName);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(pagodaName);
                    isShow = false;
                }
            }
        });
    }
}
