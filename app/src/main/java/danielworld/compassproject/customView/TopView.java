package danielworld.compassproject.customView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.namgyuworld.utility.Logger;

import danielworld.compassproject.R;
import danielworld.compassproject.activities.MainActivity;
import danielworld.compassproject.preference.CompassPreference;
import danielworld.compassproject.service.GPSTracker;
import danielworld.compassproject.util.EarthDegreeConverter;


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

    private CompassPreference mPrefs;

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

        mPrefs = new CompassPreference(context);
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

            LOG.d(TAG, "gps latitude in DD.d: " + gps.getLatitude());
            LOG.d(TAG, "gps longitude in DD.d: " + gps.getLongitude());
            LOG.d(TAG, "gps latitude in DMS: " + EarthDegreeConverter.DDtoDMSclass.DDtoDMS(gps.getLatitude()));
            LOG.d(TAG, "gps longitude in DMS: " + EarthDegreeConverter.DDtoDMSclass.DDtoDMS(gps.getLongitude()));

            double desLat = 0.0f, desLon = 0.0f; // destination latitude, longitude in DDd format
            try {
                desLat = Double.parseDouble(mPrefs.getDDdLat());
                desLon = Double.parseDouble(mPrefs.getDDdLon());
            }catch (Exception e){
            }

            LOG.d(TAG, "destination gps : " + desLat + " / " + desLon);
            LOG.d(TAG, "The distance between destination to current place (km) : " + EarthDegreeConverter.distance(gps.getLatitude(), gps.getLongitude(), desLat, desLon));

            // TEST
            Location locationA= new Location("Point A");
            locationA.setLatitude(gps.getLatitude());
            locationA.setLongitude(gps.getLongitude());
            Location locationB = new Location("Point B");
            locationB.setLatitude(desLat);
            locationB.setLongitude(desLon);
            LOG.d(TAG, "Another distance : " + locationA.distanceTo(locationB));

            // North is 0 degree and East is +90 degree, which means direction goes to right way.
            LOG.d(TAG, "InitialBearing: " + locationA.bearingTo(locationB));

            Message msg = new Message();
            msg.what = MainActivity.COMPLETE_SEARCHING_BEARING;
            msg.obj = locationA.bearingTo(locationB);
            mHandler.sendMessage(msg);

        }
    }
    /* Stop using gps tracker */
    public void stopUsingGPS(){
        gps.stopUsingGPS();
    }
}
