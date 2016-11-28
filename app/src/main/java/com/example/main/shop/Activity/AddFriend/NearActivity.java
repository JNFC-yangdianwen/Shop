package com.example.main.shop.Activity.AddFriend;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
//附近的人
public class NearActivity extends AppCompatActivity {

    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
    }
    @OnClick({R.id.iv_back,R.id.iv_near})
    public void action(View v){
        switch (v.getId()){
            case R.id.iv_back:
                finish();
            break;
            case R.id.iv_near:

                break;
        }
    }

    private void getNear() {
        //搜索附近的人
        LocationManager lm= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //定位监听
        LocationListener locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
               //状态改变时
            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
        Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        // if Location wasn't found
        if (loc == null) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);

            String provider = lm.getBestProvider(criteria, true);
            // Use the provider to get the last known location
            loc = lm.getLastKnownLocation(provider);
            double lat1 = loc.getLatitude();
            double lng1 = loc.getLongitude();

            activityUtils.showToast("定位位置。。。。。"+lat1+lng1);
        }
        double latitude = loc.getLatitude();
        double longitude = loc.getLongitude();
    }
}
