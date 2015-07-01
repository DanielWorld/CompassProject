package danielworld.compassproject;

import android.app.Activity;
import android.os.Bundle;

import danielworld.compassproject.customView.MainView;
import danielworld.compassproject.customView.TopView;
import danielworld.compassproject.service.GPSTracker;
import danielworld.compassproject.util.EarthDegreeConverter;
import danielworld.compassproject.util.Logger;
import danielworld.compassproject.util.app.AppUtil;

public class MainActivity extends Activity {

    private final String TAG = getClass().getSimpleName();
    private final Logger LOG = Logger.getInstance();


    private TopView tView;
    private MainView mView;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (AppUtil.isDebuggable(this)) {
            LOG.enableLog();
        } else {
            LOG.disableLog();
        }

        tView = (TopView) findViewById(R.id.top_view);
        mView = (MainView) findViewById(R.id.main_view);

        gps = new GPSTracker(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mView != null)
            mView.onResume();

        gps.getLocation();

        if (gps.canGetLocation()) {
            tView.setText("Latitude:" + gps.getLatitude()
                    + "\n" + "Longitude:" + gps.getLongitude());
            LOG.d(TAG, "latitude:" + gps.getLatitude());
            LOG.d(TAG, "longitude:" + gps.getLongitude());

            LOG.d(TAG, "DNS to DD:" + EarthDegreeConverter.DMStoDD(37, 52, 10));

        }

    }

    @Override
    protected void onPause() {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onPause();

        if (mView != null)
            mView.onPause();

        gps.stopUsingGPS();

    }

}
