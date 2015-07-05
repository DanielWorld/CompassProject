package danielworld.compassproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import danielworld.compassproject.R;
import danielworld.compassproject.customView.MainView;
import danielworld.compassproject.customView.TopView;
import danielworld.compassproject.service.GPSTracker;
import danielworld.compassproject.util.EarthDegreeConverter;
import danielworld.compassproject.util.Logger;
import danielworld.compassproject.util.app.AppUtil;

public class MainActivity extends Activity {

    private final String TAG = getClass().getSimpleName();
    private final Logger LOG = Logger.getInstance();

    public final static int GO_TO_SETTINGS_ACTIVITY = 22342;
    public final static int COMPLETE_SEARCHING_GPS = GO_TO_SETTINGS_ACTIVITY + 1;

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case GO_TO_SETTINGS_ACTIVITY:
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(intent);
                    break;
                case COMPLETE_SEARCHING_GPS:

                    break;
            }
        }
    };

    private TopView tView;
    private MainView mView;

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

        tView.setHandler(mHandler);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mView != null)
            mView.onResume();
    }

    @Override
    protected void onPause() {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onPause();
        if (mView != null)
            mView.onPause();
    }

}
