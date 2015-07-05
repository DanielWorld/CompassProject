package danielworld.compassproject.customView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import danielworld.compassproject.R;
import danielworld.compassproject.activities.MainActivity;
import danielworld.compassproject.service.GPSTracker;
import danielworld.compassproject.util.EarthDegreeConverter;
import danielworld.compassproject.util.Logger;


/**
 * Copyright (C) 2014-2015 Daniel Park. op7773hons@gmail.com
 * </p>
 * This file is part of CompassProject (https://github.com/DanielWorld)
 * Created by danielpark on 2015. 7. 1..
 */
public class TopView extends RelativeLayout implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();
    private Logger LOG = Logger.getInstance();

    Handler mHandler;
    static double gpsLatitude;
    static double gpsLongitude;
    GPSTracker gps;

    private ImageButton goSettingsBtn, findGPSBtn;

    public TopView(Context context) {
        super(context);
        init(context);
    }

    public TopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TopView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void setHandler(Handler h){
        this.mHandler = h;
    }

    private void init(Context context) {

        View v = LayoutInflater.from(context).inflate(R.layout.view_top, null);

        goSettingsBtn = (ImageButton) v.findViewById(R.id.go_setting_btn);  // Go to Settings Activity
        findGPSBtn = (ImageButton) v.findViewById(R.id.search_gps_btn); // find current gps

        goSettingsBtn.setOnClickListener(this);
        findGPSBtn.setOnClickListener(this);
        addView(v);

        gps = new GPSTracker(context); // Initialize GPS
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go_setting_btn:
                mHandler.sendEmptyMessage(MainActivity.GO_TO_SETTINGS_ACTIVITY);
                break;
            case R.id.search_gps_btn:
                getGPSLocation(); // get the latest gps location
                mHandler.sendEmptyMessage(MainActivity.COMPLETE_SEARCHING_GPS);
                break;
        }
    }
    /* Get current gps location */
    public void getGPSLocation(){
        gps.getLocation();
        if(gps.canGetLocation()){
            gpsLatitude = gps.getLatitude(); // DD.dddddd format
            gpsLongitude = gps.getLongitude(); // DD.dddddd format

            LOG.d(TAG, "gps latitude in DMS: " + EarthDegreeConverter.DDtoDMSclass.DDtoDMS(gps.getLatitude()));
            LOG.d(TAG, "gps longitude in DMS: " + EarthDegreeConverter.DDtoDMSclass.DDtoDMS(gps.getLongitude()));
            LOG.d(TAG, "gps latitude in DD.d: " + gps.getLatitude());
            LOG.d(TAG, "gps longitude in DD.d: " + gps.getLongitude());
        }
    }
    /* Stop using gps tracker */
    public void stopUsingGPS(){
        gps.stopUsingGPS();
    }
}
